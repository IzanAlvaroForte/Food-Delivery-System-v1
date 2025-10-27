package com.example.Food_Delivery_System_Cashless.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "rider_table")
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String riderName;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Must be at least 10-15")
    @Pattern(regexp = "^[0-9]+$", message = "Phone contain only numbers")
    private String riderPhoneNumber;

    @NotBlank(message = "Email is required")
    @Email
    private String riderEmail;

    @Pattern(regexp = "BICYCLE|MOTORCYCLE|CAR", message = "Invalid vehicle type")
    private String vehicleType;

    @NotBlank(message = "Vehicle Plate is required")
    @Size(min = 5, max = 7, message = "Minimum plate number is at least 5")
    private String vehiclePlate;

    @Pattern(regexp = "AVAILABLE|BUSY|OFFLINE", message = "Invalid rider status")
    private String riderStatus;

    private LocalDateTime createdAt;
    private Boolean isActive;

    @OneToMany(mappedBy = "rider")
    private List<Order> orders;

    public Rider() {
    }

    public Rider(Long id, String riderName, String riderPhoneNumber, String riderEmail, String vehicleType, String vehiclePlate, String riderStatus, LocalDateTime createdAt, Boolean isActive, List<Order> orders) {
        this.id = id;
        this.riderName = riderName;
        this.riderPhoneNumber = riderPhoneNumber;
        this.riderEmail = riderEmail;
        this.vehicleType = vehicleType;
        this.vehiclePlate = vehiclePlate;
        this.riderStatus = riderStatus;
        this.createdAt = createdAt;
        this.isActive = isActive;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public String getRiderPhoneNumber() {
        return riderPhoneNumber;
    }

    public void setRiderPhoneNumber(String riderPhoneNumber) {
        this.riderPhoneNumber = riderPhoneNumber;
    }

    public String getRiderEmail() {
        return riderEmail;
    }

    public void setRiderEmail(String riderEmail) {
        this.riderEmail = riderEmail;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehiclePlate() {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate) {
        this.vehiclePlate = vehiclePlate;
    }

    public String getRiderStatus() {
        return riderStatus;
    }

    public void setRiderStatus(String riderStatus) {
        this.riderStatus = riderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
