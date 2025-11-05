package com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO;

import java.time.LocalDateTime;

public class CustomerResponseCreateDTO {

    private Long id;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNum;
    private String deliveryAddress;
    private LocalDateTime createdWhen;

    public CustomerResponseCreateDTO(Long id, String customerName, String customerEmail, String customerPhoneNum, String deliveryAddress, LocalDateTime createdWhen) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNum = customerPhoneNum;
        this.deliveryAddress = deliveryAddress;
        this.createdWhen = createdWhen;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhoneNum() {
        return customerPhoneNum;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDateTime getCreatedWhen() {
        return createdWhen;
    }
}
