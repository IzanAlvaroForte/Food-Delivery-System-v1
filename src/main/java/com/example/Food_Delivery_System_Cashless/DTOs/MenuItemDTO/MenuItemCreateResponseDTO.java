package com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MenuItemCreateResponseDTO {

    private Long id;
    private String menuItemName;
    private String description;
    private BigDecimal price;
    private String category;
    private Boolean isAvailable;
    private LocalDateTime createdWhen;

    public MenuItemCreateResponseDTO(Long id, String menuItemName, String description, BigDecimal price, String category, Boolean isAvailable, LocalDateTime createdWhen) {
        this.id = id;
        this.menuItemName = menuItemName;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
        this.createdWhen = createdWhen;
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

    public LocalDateTime getCreatedWhen() {
        return createdWhen;
    }
}
