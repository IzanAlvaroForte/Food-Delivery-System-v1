package com.example.Food_Delivery_System_Cashless.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<com.example.Food_Delivery_System_Cashless.Models.Order, Long> {
    Optional<com.example.Food_Delivery_System_Cashless.Models.Order> findByOrderNumber(String orderNumber);
    List<com.example.Food_Delivery_System_Cashless.Models.Order> findByStatus(String status);
    List<com.example.Food_Delivery_System_Cashless.Models.Order> findByCustomerId(Long customerId);
    List<com.example.Food_Delivery_System_Cashless.Models.Order> findByRestaurantId(Long restaurantId);
}
