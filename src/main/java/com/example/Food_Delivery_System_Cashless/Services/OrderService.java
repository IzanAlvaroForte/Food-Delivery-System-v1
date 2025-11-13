package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderCreateResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderProgressDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderRequestDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderItemDTO.OrderItemResponseDTO;
import com.example.Food_Delivery_System_Cashless.Models.*;
import com.example.Food_Delivery_System_Cashless.Repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

        // Convert OrderItems to ResponseDTOs
        List<OrderItemResponseDTO> orderItemDTOs = savedFoodOrder.getOrderItems().stream()
                .map(orderItem -> new OrderItemResponseDTO(
                        orderItem.getId(),
                        orderItem.getMenuItem().getMenuItemName(),
                        orderItem.getOrderQuantity(),
                        orderItem.getOrderPrice(),
                        orderItem.getOrderPrice().multiply(BigDecimal.valueOf(orderItem.getOrderQuantity())),
                        orderItem.getSpecialInstructions()
                ))
                .collect(Collectors.toList());

        // Create OrderProgressDTO
        OrderProgressDTO progress = new OrderProgressDTO(
                savedFoodOrder.getOrderDate(),
                savedFoodOrder.getConfirmedAt(),
                savedFoodOrder.getPreparingAt(),
                savedFoodOrder.getReadyAt(),
                savedFoodOrder.getAssignedAt(),
                savedFoodOrder.getPickedUpAt(),
                savedFoodOrder.getDeliveredAt(),
                savedFoodOrder.getCancelledAt()
        );

        return new OrderCreateResponseDTO(
                savedFoodOrder.getId(),
                savedFoodOrder.getOrderNumber(),
                savedFoodOrder.getTotalAmount(),
                savedFoodOrder.getStatus(),
                savedFoodOrder.getDeliveryAddress(),
                savedFoodOrder.getRestaurant().getId(),
                savedFoodOrder.getRestaurant().getRestaurantName(),
                savedFoodOrder.getRider() != null ? savedFoodOrder.getRider().getId() : null,
                savedFoodOrder.getRider() != null ? savedFoodOrder.getRider().getRiderName() : null,
                savedFoodOrder.getCustomer().getId(),
                savedFoodOrder.getCustomer().getCustomerName(),
                progress,
                orderItemDTOs
        );

    }

    public String createOrderNumber() {
        return "ORD-" + UUID.randomUUID()
                .toString().substring(0,5);
    }
}
