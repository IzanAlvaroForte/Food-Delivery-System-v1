package com.example.Food_Delivery_System_Cashless.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_item_table")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Order is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order foodOrder;

    @NotNull(message = "Menu item is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_item_id", nullable = false)
    private MenuItem menuItem;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Max(value = 100, message = "Quantity must not exceed 100")
    private Integer orderQuantity;

    @NotNull(message = "Order price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Order price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Order price format invalid")
    private BigDecimal orderPrice; // Price at time of order

    @Size(max = 255, message = "Special instructions must not exceed 255 characters")
    private String specialInstructions;

    public OrderItem() {
    }

    public OrderItem(Long id, Order foodOrder, MenuItem menuItem, Integer orderQuantity, BigDecimal orderPrice, String specialInstructions) {
        this.id = id;
        this.foodOrder = foodOrder;
        this.menuItem = menuItem;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.specialInstructions = specialInstructions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getFoodOrder() {
        return foodOrder;
    }

    public void setOrder(Order foodOrder) {
        this.foodOrder = foodOrder;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
}
