package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemCreateResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemDeleteResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemRequestDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemUpdateResponseDTO;
import com.example.Food_Delivery_System_Cashless.Models.MenuItem;
import com.example.Food_Delivery_System_Cashless.Models.Restaurant;
import com.example.Food_Delivery_System_Cashless.Repository.MenuItemRepository;
import com.example.Food_Delivery_System_Cashless.Repository.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    public MenuItemService(MenuItemRepository menuItemRepository,
                           RestaurantRepository restaurantRepository) {
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Transactional
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

    @Transactional
    public MenuItemDeleteResponseDTO softDeleteMenuItem(Long id) {
        MenuItem checkForMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No menu exist!"));
        checkForMenuItem.setAvailable(false);

        MenuItem savedMenuItem = menuItemRepository.save(checkForMenuItem);

        return new MenuItemDeleteResponseDTO(
                savedMenuItem.getId(),
                savedMenuItem.getMenuItemName(),
                "SOFT-DELETED",
                LocalDateTime.now()
        );
    }

    @Transactional
    public void hardDeleteMenuItem(Long id) {
        MenuItem checkForMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No menu exist!"));
        menuItemRepository.delete(checkForMenuItem);

    }

    public List<MenuItemCreateResponseDTO> getAllMenuItem(int page, int size, String sort) {
        String[] sortParameter = sort.split(",");
        Sort.Direction direction = sortParameter[1]
                .equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page,size,
                Sort.by(direction, sortParameter[0]));

        Page<MenuItem> menuItemPage = menuItemRepository.findAll(pageable);

        return menuItemPage.getContent().stream()
                .map(theMenuItem -> new MenuItemCreateResponseDTO(
                        theMenuItem.getId(),
                        theMenuItem.getMenuItemName(),
                        theMenuItem.getDescription(),
                        theMenuItem.getPrice(),
                        theMenuItem.getCategory(),
                        theMenuItem.getAvailable(),
                        theMenuItem.getCreatedAt()
                )).collect(Collectors.toList());

    }

    public MenuItemCreateResponseDTO getMenuItem(Long id) {

        MenuItem viewMenuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Menu Item Found!"));
        return new MenuItemCreateResponseDTO(
                viewMenuItem.getId(),
                viewMenuItem.getMenuItemName(),
                viewMenuItem.getDescription(),
                viewMenuItem.getPrice(),
                viewMenuItem.getCategory(),
                viewMenuItem.getAvailable(),
                viewMenuItem.getCreatedAt()
        );
    }

}
