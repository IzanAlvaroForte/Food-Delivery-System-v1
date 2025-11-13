package com.example.Food_Delivery_System_Cashless.Repository;

import com.example.Food_Delivery_System_Cashless.Models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
