package com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO;

import com.example.Food_Delivery_System_Cashless.DTOs.OrderItemDTO.OrderItemResponseDTO;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreateResponseDTO {

    private Long id;
    private String orderNumber;
    private BigDecimal totalAmount;
    private String status;
    private String deliveryAddress;

    private Long restaurantId;
    private String restaurantName;

    private Long riderId;
    private String riderName;

    private Long customerId;
    private String customerName;

    private OrderProgressDTO timeProgress;

    private List<OrderItemResponseDTO> orderItems;
    public OrderCreateResponseDTO() {
    }

    public OrderCreateResponseDTO(Long id, String orderNumber, BigDecimal totalAmount, String status, String deliveryAddress, Long restaurantId, String restaurantName, Long riderId, String riderName, Long customerId, String customerName, OrderProgressDTO timeProgress, List<OrderItemResponseDTO> orderItems) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.totalAmount = totalAmount;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.riderId = riderId;
        this.riderName = riderName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.timeProgress = timeProgress;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public Long getRiderId() {
        return riderId;
    }

    public String getRiderName() {
        return riderName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderProgressDTO getTimeProgress() {
        return timeProgress;
    }

    public List<OrderItemResponseDTO> getOrderItems() {
        return orderItems;
    }
}
