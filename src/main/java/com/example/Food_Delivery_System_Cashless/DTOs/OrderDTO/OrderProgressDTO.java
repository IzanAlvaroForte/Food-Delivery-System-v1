package com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO;

import jakarta.persistence.criteria.Order;

import java.time.LocalDateTime;

public class OrderProgressDTO {
    private LocalDateTime orderDate;

    private LocalDateTime confirmedAt;
    private LocalDateTime preparingAt;
    private LocalDateTime readyAt;
    private LocalDateTime assignedAt;
    private LocalDateTime pickedUpAt;
    private LocalDateTime deliveredAt;
    private LocalDateTime cancelledAt;

    public OrderProgressDTO(Order order) {
        this.orderDate = orderDate;
        this.confirmedAt = confirmedAt;
        this.preparingAt = preparingAt;
        this.readyAt = readyAt;
        this.assignedAt = assignedAt;
        this.pickedUpAt = pickedUpAt;
        this.deliveredAt = deliveredAt;
        this.cancelledAt = cancelledAt;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public LocalDateTime getPreparingAt() {
        return preparingAt;
    }

    public LocalDateTime getReadyAt() {
        return readyAt;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public LocalDateTime getPickedUpAt() {
        return pickedUpAt;
    }

    public LocalDateTime getDeliveredAt() {
        return deliveredAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }
}
