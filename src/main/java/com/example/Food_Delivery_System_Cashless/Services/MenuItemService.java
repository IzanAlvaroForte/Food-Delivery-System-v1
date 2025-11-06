package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemCreateResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemRequestDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemUpdateResponseDTO;
import com.example.Food_Delivery_System_Cashless.Models.MenuItem;
import com.example.Food_Delivery_System_Cashless.Models.Restaurant;
import com.example.Food_Delivery_System_Cashless.Repository.MenuItemRepository;
import com.example.Food_Delivery_System_Cashless.Repository.RestaurantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    public MenuItemService(MenuItemRepository menuItemRepository,
                           RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public MenuItemCreateResponseDTO createMenuItem(MenuItemRequestDTO menuItemRequestDTO) {

        Restaurant findRestoId = restaurantRepository.findById(menuItemRequestDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant Doesn't exist"));

        MenuItem displayMenu = new MenuItem();
        displayMenu.setMenuItemName(menuItemRequestDTO.getMenuItemName());
        displayMenu.setDescription(menuItemRequestDTO.getDescription());
        displayMenu.setAvailable(menuItemRequestDTO.getAvailable());
        displayMenu.setPrice(menuItemRequestDTO.getPrice());
        displayMenu.setRestaurant(findRestoId);

        MenuItem savedMenu = menuItemRepository.save(displayMenu);

        return new MenuItemCreateResponseDTO(
                savedMenu.getId(),
                savedMenu.getMenuItemName(),
                savedMenu.getDescription(),
                savedMenu.getPrice(),
                savedMenu.getCategory(),
                savedMenu.getAvailable(),
                savedMenu.getCreatedAt()
        );

    }

    @Transactional
    public MenuItemUpdateResponseDTO updateResponseDTO(Long id,
                                                       MenuItemRequestDTO menuItemRequestDTO) {
        MenuItem updateTheMenu = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No existing Menu found"));
        updateTheMenu.setMenuItemName(menuItemRequestDTO.getMenuItemName());
        updateTheMenu.setDescription(menuItemRequestDTO.getDescription());
        updateTheMenu.setPrice(menuItemRequestDTO.getPrice());
        updateTheMenu.setAvailable(menuItemRequestDTO.getAvailable());

        MenuItem savedUpdateMenu = menuItemRepository.save(updateTheMenu);

        return new MenuItemUpdateResponseDTO(
                savedUpdateMenu.getId(),
                savedUpdateMenu.getMenuItemName(),
                savedUpdateMenu.getDescription(),
                savedUpdateMenu.getPrice(),
                savedUpdateMenu.getCategory(),
                savedUpdateMenu.getAvailable(),
                savedUpdateMenu.getCreatedAt()
        );
    }
}
