package com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO;

import java.time.LocalDateTime;

public class MenuItemDeleteResponseDTO {

    private Long id;
    private String menuItemName;
    private String status;
    private LocalDateTime deletedAt;

    public MenuItemDeleteResponseDTO(Long id, String menuItemName, String status, LocalDateTime deletedAt) {
        this.id = id;
        this.menuItemName = menuItemName;
        this.status = status;
        this.deletedAt = deletedAt;
    }

    public Long getId() {
        return id;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
