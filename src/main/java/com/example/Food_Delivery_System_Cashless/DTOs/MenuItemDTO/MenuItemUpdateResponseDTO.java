package com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MenuItemUpdateResponseDTO {

    private Long id;
    private String menuItemName;
    private String description;
    private BigDecimal price;
    private String category;
    private Boolean isAvailable;
    private LocalDateTime updatedWhen;

    public MenuItemUpdateResponseDTO(Long id, String menuItemName, String description, BigDecimal price, String category, Boolean isAvailable, LocalDateTime updatedWhen) {
        this.id = id;
        this.menuItemName = menuItemName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
        this.updatedWhen = updatedWhen;
    }

    public Long getId() {
        return id;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public LocalDateTime getUpdatedWhen() {
        return updatedWhen;
    }
}
