package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.Repository.RiderRepository;

public class RiderService {

    private final RiderRepository riderRepository;
    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }
}
