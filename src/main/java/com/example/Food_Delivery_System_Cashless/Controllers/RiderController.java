package com.example.Food_Delivery_System_Cashless.Controllers;

import com.example.Food_Delivery_System_Cashless.Services.RiderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RiderController {

    private final RiderService riderService;
    public RiderController(RiderService riderService) {
        this.riderService = riderService;
    }
}
