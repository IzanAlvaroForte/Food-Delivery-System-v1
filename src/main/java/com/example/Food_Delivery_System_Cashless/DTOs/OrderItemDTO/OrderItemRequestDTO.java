package com.example.Food_Delivery_System_Cashless.DTOs.OrderItemDTO;

import jakarta.validation.constraints.*;

public class OrderItemRequestDTO {

    @NotNull(message = "Menu item ID is required")
    private Long menuItemId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100, message = "Quantity cannot exceed 100")
    private Integer quantity;

    @Size(max = 500, message = "Special instructions must not exceed 500 characters")
    private String specialInstructions;

    public OrderItemRequestDTO() {
    }

    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

    public Long getMenuItemId() {
        return menuItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }
}
