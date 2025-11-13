package com.example.Food_Delivery_System_Cashless.Controllers;

import com.example.Food_Delivery_System_Cashless.Services.OrderItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/orders/")
public class OrderItemController {

    private final OrderItemService orderItemService;
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
}
