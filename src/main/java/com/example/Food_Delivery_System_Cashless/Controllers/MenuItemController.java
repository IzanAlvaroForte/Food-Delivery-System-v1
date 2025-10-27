package com.example.Food_Delivery_System_Cashless.Controllers;

import com.example.Food_Delivery_System_Cashless.Services.MenuItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MenuItemController {

    private final MenuItemService menuItemService;
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService =menuItemService;
    }
}
