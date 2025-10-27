package com.example.Food_Delivery_System_Cashless.Repository;

import com.example.Food_Delivery_System_Cashless.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customer, Long> {
}
