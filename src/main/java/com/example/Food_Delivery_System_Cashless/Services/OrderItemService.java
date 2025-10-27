package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.Repository.OrderItemRepository;

public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }
}
