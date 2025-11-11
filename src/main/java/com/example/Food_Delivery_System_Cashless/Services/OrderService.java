package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderCreateResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderRequestDTO;
import com.example.Food_Delivery_System_Cashless.Models.*;
import com.example.Food_Delivery_System_Cashless.Repository.*;

import java.util.UUID;

public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CustomerRespository customerRespository;
    private final RestaurantRepository restaurantRepository;
    private final RiderRepository riderRepository;

    public OrderService(
            OrderRepository orderRepository,
            OrderItemRepository orderItemRepository,
            CustomerRespository customerRespository,
            RestaurantRepository restaurantRepository,
            RiderRepository riderRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.customerRespository = customerRespository;
        this.restaurantRepository = restaurantRepository;
        this.riderRepository = riderRepository;
    }

    public OrderCreateResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        Customer findCustomer = customerRespository.findById(orderRequestDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("No existing customer"));
        Restaurant findResto = restaurantRepository.findById(orderRequestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("No existing restaurant"));
        Rider findRider = riderRepository.findById(orderRequestDTO.getRiderId())
                .orElseThrow(() -> new RuntimeException("No existing rider"));

        Order newOrder = new Order();
        newOrder.setOrderNumber(createOrderNumber());
        newOrder.setCustomer(findCustomer);
        newOrder.setRestaurant(findResto);
        newOrder.setRider(findRider);
        newOrder.setDeliveryAddress(orderRequestDTO.getDeliveryAddress());

        if (orderRequestDTO.getRiderId() != null) {
            Rider rider = riderRepository.findById(orderRequestDTO.getRiderId())
                    .orElseThrow(() -> new RuntimeException("Rider not found"));
            newOrder.setRider(rider);
        }
        newOrder.setStatus("PENDING");


    }

    public String createOrderNumber() {
        return "ORD-" + UUID.randomUUID()
                .toString().substring(0,5);
    }
}
