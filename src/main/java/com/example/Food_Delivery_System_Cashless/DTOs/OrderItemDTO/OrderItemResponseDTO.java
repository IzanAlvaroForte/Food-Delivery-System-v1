package com.example.Food_Delivery_System_Cashless.DTOs.OrderItemDTO;

import java.math.BigDecimal;

public class OrderItemResponseDTO {

    private Long id;

    private String nameMenuItem;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private String specialInstruction;

    public OrderItemResponseDTO(Long id, String nameMenuItem, Integer quantity, BigDecimal price, BigDecimal totalAmount, String specialInstruction) {
        this.id = id;
        this.nameMenuItem = nameMenuItem;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.specialInstruction = specialInstruction;
    }

    public Long getId() {
        return id;
    }

    public String getNameMenuItem() {
        return nameMenuItem;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getSpecialInstruction() {
        return specialInstruction;
    }
}
