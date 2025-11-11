package com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO;

import com.example.Food_Delivery_System_Cashless.DTOs.OrderItemDTO.OrderItemRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderRequestDTO {

    @NotNull(message = "Customer id is required")
    private Long customerId;

    @NotNull(message = "restaurant id is required")
    private Long restaurantId;

    @NotNull(message = "Rider id is required")
    private Long riderId;

    @NotBlank(message = "Address is required")
    private String deliveryAddress;

    @NotEmpty(message = "Order should contain at least one item")
    private List<OrderItemRequestDTO> items;

    public OrderRequestDTO() {
    }

    public OrderRequestDTO(Long customer_id, Long restaurant_id, Long rider_id, String deliveryAddress, List<OrderItemRequestDTO> items) {
        this.customerId = customer_id;
        this.restaurantId = restaurant_id;
        this.riderId = rider_id;
        this.deliveryAddress = deliveryAddress;
        this.items = items;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Long getRiderId() {
        return riderId;
    }

    public void setRiderId(Long riderId) {
        this.riderId = riderId;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<OrderItemRequestDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDTO> items) {
        this.items = items;
    }
}
