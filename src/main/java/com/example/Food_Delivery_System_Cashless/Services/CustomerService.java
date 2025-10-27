package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.Repository.CustomerRespository;

public class CustomerService {

    private final CustomerRespository customerRespository;
    public CustomerService(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }
}
