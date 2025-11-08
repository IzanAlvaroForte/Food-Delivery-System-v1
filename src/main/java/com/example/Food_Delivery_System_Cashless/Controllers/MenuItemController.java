package com.example.Food_Delivery_System_Cashless.Controllers;

import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemCreateResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemDeleteResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemRequestDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO.MenuItemUpdateResponseDTO;
import com.example.Food_Delivery_System_Cashless.Services.MenuItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/menu-items")
public class MenuItemController {

    private final MenuItemService menuItemService;
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MenuItemCreateResponseDTO> createMenuItemDTO(
            @RequestBody MenuItemRequestDTO menuItemRequestDTO) {

        MenuItemCreateResponseDTO savedCreatedMenuItem = menuItemService.createMenuItem(menuItemRequestDTO);
        return ResponseEntity.ok(savedCreatedMenuItem);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MenuItemUpdateResponseDTO> updateMenuitemDTO(
            @PathVariable Long id, @RequestBody MenuItemRequestDTO menuItemRequestDTO) {

        MenuItemUpdateResponseDTO savedUpdatedMenuItem = menuItemService.updateResponseDTO(id, menuItemRequestDTO);
        return ResponseEntity.ok(savedUpdatedMenuItem);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hardDeletion(
           @PathVariable Long id) {
        menuItemService.hardDeleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/availability")
    public ResponseEntity<MenuItemDeleteResponseDTO> softDeletion(
            @PathVariable Long id) {
        MenuItemDeleteResponseDTO savedSoftDeletion = menuItemService.softDeleteMenuItem(id);
        return ResponseEntity.ok(savedSoftDeletion);
    }

    @GetMapping("/view-all-menu-items")
    public ResponseEntity<List<MenuItemCreateResponseDTO>> viewAllMenuItem (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "event.asc") String sort) {

        return ResponseEntity.ok(menuItemService.getAllMenuItem(page, size, sort));
    }

    @RequestMapping("/{id}")
    public ResponseEntity<MenuItemCreateResponseDTO> viewMenuItem(
            @PathVariable Long id) {
        MenuItemCreateResponseDTO viewMenuItem = menuItemService.getMenuItem(id);
        return ResponseEntity.ok(viewMenuItem);
    }
}
