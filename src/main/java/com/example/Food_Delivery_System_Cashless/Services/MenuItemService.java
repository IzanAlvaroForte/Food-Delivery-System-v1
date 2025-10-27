package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.Repository.MenuItemRepository;

public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }
}
