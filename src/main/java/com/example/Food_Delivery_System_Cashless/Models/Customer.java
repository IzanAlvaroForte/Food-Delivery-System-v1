package com.example.Food_Delivery_System_Cashless.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table (name = "customer_table")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String customerName;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone must be 10-15 digits")
    @Pattern(regexp = "^[0-9]+$", message = "Phone must contain only numbers")
    private String customerPhone;

    @NotBlank(message = "Email is required")
    @Email
    private String customerEmail;

    @NotBlank(message = "Delivery address must be required")
    private String deliveryAddress;

    private LocalDateTime createdAt;

    public Customer() {
    }

    public Customer(Long id, String customerName, String customerPhone, String customerEmail, String deliveryAddress, LocalDateTime createdAt) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.customerEmail = customerEmail;
        this.deliveryAddress = deliveryAddress;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
