package com.example.Food_Delivery_System_Cashless.Repository;

import com.example.Food_Delivery_System_Cashless.Models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRespository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCustomerEmailOrPhone(String customerEmail, String customerPhoneNum);
}
