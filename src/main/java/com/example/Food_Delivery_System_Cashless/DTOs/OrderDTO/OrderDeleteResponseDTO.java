package com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO;

import java.time.LocalDateTime;

public class OrderDeleteResponseDTO {

    private String message;
    private LocalDateTime deletedAt;

    public OrderDeleteResponseDTO(String message, LocalDateTime deletedAt) {
        this.message = message;
        this.deletedAt = deletedAt;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
