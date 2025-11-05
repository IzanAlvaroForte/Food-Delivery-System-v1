package com.example.Food_Delivery_System_Cashless.Controllers;

import com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO.CustomerRequestDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO.CustomerResponseCreateDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO.CustomerResponseUpdateDTO;
import com.example.Food_Delivery_System_Cashless.Services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/customer")
public class CustomerController {

    private final CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerResponseCreateDTO> createCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO) {

        CustomerResponseCreateDTO createResult = customerService.createCustomer(customerRequestDTO);
        return ResponseEntity.ok(createResult);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CustomerResponseUpdateDTO> updateCustomer(
            @RequestBody CustomerRequestDTO customerRequestDTO,
            @PathVariable Long id) {

        CustomerResponseUpdateDTO updateResult = customerService.updateCustomer(id, customerRequestDTO);
        return ResponseEntity.ok(updateResult);
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseCreateDTO>> listOfCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(customerService.listOfCustomers(page, size));
    }
}
