package com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO;

import java.time.LocalDateTime;

public class CustomerResponseUpdateDTO {

    private Long id;
    private String customerName;
    private String customerPhoneNum;
    private String customerEmail;
    private String deliveryAddress;
    private LocalDateTime updatedWhen;

    public CustomerResponseUpdateDTO(Long id, String customerName, String customerPhoneNum, String customerEmail, String deliveryAddress, LocalDateTime updatedWhen) {
        this.id = id;
        this.customerName = customerName;
        this.customerPhoneNum = customerPhoneNum;
        this.customerEmail = customerEmail;
        this.deliveryAddress = deliveryAddress;
        this.updatedWhen = updatedWhen;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerPhoneNum() {
        return customerPhoneNum;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDateTime getUpdatedWhen() {
        return updatedWhen;
    }
}
