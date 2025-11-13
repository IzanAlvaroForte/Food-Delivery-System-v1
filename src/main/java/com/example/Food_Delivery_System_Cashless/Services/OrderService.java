package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderCreateResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderDeleteResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderProgressDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderRequestDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderItemDTO.OrderItemResponseDTO;
import com.example.Food_Delivery_System_Cashless.Models.*;
import com.example.Food_Delivery_System_Cashless.Repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRespository customerRespository;
    private final RestaurantRepository restaurantRepository;
    private final RiderRepository riderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CustomerRespository customerRespository,
            RestaurantRepository restaurantRepository,
            RiderRepository riderRepository,
            MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRespository = customerRespository;
        this.restaurantRepository = restaurantRepository;
        this.riderRepository = riderRepository;
        this.menuItemRepository = menuItemRepository;
    }
    
    @Transactional
    public OrderCreateResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Customer findCustomer = customerRespository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("No existing customer"));
        Restaurant findResto = restaurantRepository.findById(orderRequestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("No existing restaurant"));

        Order newOrder = new Order();
        newOrder.setOrderNumber(createOrderNumber());
        newOrder.setCustomer(findCustomer);
        newOrder.setRestaurant(findResto);
        newOrder.setDeliveryAddress(orderRequestDTO.getDeliveryAddress());

        if (orderRequestDTO.getRiderId() != null) {
            Rider rider = riderRepository.findById(orderRequestDTO.getRiderId())
                    .orElseThrow(() -> new RuntimeException("Rider not found"));
            newOrder.setRider(rider);
        }

        newOrder.setStatus("PENDING");

        List<OrderItem> orderItems = orderRequestDTO.getItems().stream()
                .map(itemRequest -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(newOrder);

                    MenuItem findMenuItem = menuItemRepository.findById(itemRequest.getMenuItemId())
                            .orElseThrow(() -> new RuntimeException(
                                    "Menu Item not found: " + itemRequest.getMenuItemId()));
                    orderItem.setMenuItem(findMenuItem);
                    orderItem.setOrderQuantity(itemRequest.getQuantity());
                    orderItem.setOrderPrice(findMenuItem.getPrice());
                    orderItem.setSpecialInstructions(itemRequest.getSpecialInstructions());

                    return orderItem;
                }).collect(Collectors.toList());
        newOrder.setOrderItems(orderItems);

        BigDecimal totalAmount = orderItems.stream()
                .map(item -> item.getOrderPrice()
                        .multiply(BigDecimal.valueOf(item.getOrderQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        newOrder.setTotalAmount(totalAmount);

        Order savedFoodOrder = orderRepository.save(newOrder);
        return convertToCreateResponseDTO(savedFoodOrder);
    }

    // For finding order ID
    public OrderCreateResponseDTO getOrderById(Long id) {
        Order findOrderId = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Id doesn't exist"));
        return convertToCreateResponseDTO(findOrderId);
    }

    //For finding order Number
    public OrderCreateResponseDTO getOrderByNumber(String orderNumber) {
        com.example.Food_Delivery_System_Cashless.Models.Order findOrderNumber =
               orderRepository.findByOrderNumber(orderNumber)
                        .orElseThrow(() -> new RuntimeException("Order Number doesn't exist"));
        return convertToCreateResponseDTO(findOrderNumber);
    }

    // For updating order
    @Transactional
    public OrderCreateResponseDTO updateOrder(Long id, String newStatus) {
        Order orderStats = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Id doesn't Exist"));

        String oldStatus = orderStats.getStatus();
        orderStats.setStatus(newStatus);

        switch (newStatus) {
            case "CONFIRMED":
                orderStats.setConfirmedAt(LocalDateTime.now());
                break;
            case "PREPARING":
                orderStats.setPreparingAt(LocalDateTime.now());
                break;
            case "READY":
                orderStats.setReadyAt(LocalDateTime.now());
                break;
            case "PICKED_UP":
                orderStats.setPickedUpAt(LocalDateTime.now());
                break;
            case "DELIVERED":
                orderStats.setDeliveredAt(LocalDateTime.now());
                break;
        }

        Order savedOrder = orderRepository.save(orderStats);
        return convertToCreateResponseDTO(savedOrder);
    }

    // For cancelling order
    @Transactional
    public OrderCreateResponseDTO cancelOrder(Long id, String cancelReason, String cancelledBy) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Id doesn't exist"));

        if (!order.getStatus().equals("PENDING")
                && !order.getStatus().equals("CONFIRMED")) {
            throw new RuntimeException("Order cannot be cancelled in current status: " + order.getStatus());
        }

        order.setStatus("CANCELLED");
        order.setCancelledBy(cancelledBy);
        order.setCancelReason(cancelReason);
        order.setCancelledAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);
        return convertToCreateResponseDTO(savedOrder);
    }

    // For assigning Rider
    @Transactional
    public OrderCreateResponseDTO assignRider(Long id, Long riderId) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Id doesn't exist"));
        Rider rider = riderRepository.findById(riderId)
                .orElseThrow(() -> new RuntimeException("Rider doesn't exist"));

        order.setRider(rider);
        order.setAssignedAt(LocalDateTime.now());
        order.setStatus("PICKED_UP");

        Order savedOrder = orderRepository.save(order);
        return convertToCreateResponseDTO(savedOrder);
    }

    // For deleting order
    public OrderDeleteResponseDTO deleteOrderByAdmin(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Id doesn't exist"));
        orderRepository.delete(order);

        return new OrderDeleteResponseDTO(
                "Order Successfully deleted",
                LocalDateTime.now());
    }

    public List<OrderCreateResponseDTO> getOrderStatusByAdmin(String status) {
        return orderRepository.findByStatus(status)
                .stream().map(this::convertToCreateResponseDTO)
                .collect(Collectors.toList());
    }

    public List<OrderCreateResponseDTO> getOrdersByCustomers(Long customerId) {
        return orderRepository.findByCustomerId(customerId)
                .stream().map(this::convertToCreateResponseDTO)
                .collect(Collectors.toList());
    }

    public List<OrderCreateResponseDTO> getOrdersByRestaurant(Long restaurantId) {
        // ✅ FIXED
        return orderRepository.findByRestaurantId(restaurantId)
                .stream().map(this::convertToCreateResponseDTO)
                .collect(Collectors.toList());
    }

    public String createOrderNumber() {
        return "ORD-" + UUID.randomUUID()
                .toString().substring(0,5);
    }

    private OrderCreateResponseDTO convertToCreateResponseDTO(Order order) {
        List<OrderItemResponseDTO> orderItemDTOs = order.getOrderItems().stream()
                .map(this::convertToOrderItemDTO)
                .collect(Collectors.toList());

        OrderProgressDTO progress = new OrderProgressDTO(
                order.getOrderDate(),
                order.getConfirmedAt(),
                order.getPreparingAt(),
                order.getReadyAt(),
                order.getAssignedAt(),
                order.getPickedUpAt(),
                order.getDeliveredAt(),
                order.getCancelledAt()
        );

        return new OrderCreateResponseDTO(
                order.getId(),
                order.getOrderNumber(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getDeliveryAddress(),
                order.getRestaurant().getId(),
                order.getRestaurant().getRestaurantName(),
                order.getRider() != null ? order.getRider().getId() : null,
                order.getRider() != null ? order.getRider().getRiderName() : null,
                order.getCustomer().getId(),
                order.getCustomer().getCustomerName(),
                progress,
                orderItemDTOs
        );
    }

    private OrderItemResponseDTO convertToOrderItemDTO(OrderItem orderItem) {
        return new OrderItemResponseDTO(
                orderItem.getId(),
                orderItem.getMenuItem().getMenuItemName(),
                orderItem.getOrderQuantity(),
                orderItem.getOrderPrice(),
                orderItem.getOrderPrice().multiply(BigDecimal.valueOf(orderItem.getOrderQuantity())),
                orderItem.getSpecialInstructions()
        );
    }
}
