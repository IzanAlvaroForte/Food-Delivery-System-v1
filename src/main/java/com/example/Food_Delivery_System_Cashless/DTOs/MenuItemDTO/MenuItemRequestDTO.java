package com.example.Food_Delivery_System_Cashless.DTOs.MenuItemDTO;

import com.example.Food_Delivery_System_Cashless.Models.Restaurant;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class MenuItemRequestDTO {

    @NotBlank(message = "Menu Item Name is required")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String menuItemName;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 6, fraction = 2, message = "Price format invalid")
    private BigDecimal price;

    private Boolean isAvailable;

    @NotNull(message = "Restaurant ID is required")
    private Long restaurantId;

    public MenuItemRequestDTO() {
    }

    public MenuItemRequestDTO(String menuItemName, String description, BigDecimal price, Boolean isAvailable, Long restaurantId) {
        this.menuItemName = menuItemName;
        this.description = description;
        this.price = price;
        this.isAvailable = isAvailable;
        this.restaurantId = restaurantId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }


}
