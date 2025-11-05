package com.example.Food_Delivery_System_Cashless.Services;

import com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO.CustomerRequestDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO.CustomerResponseCreateDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.CustomerDTO.CustomerResponseUpdateDTO;
import com.example.Food_Delivery_System_Cashless.Models.Customer;
import com.example.Food_Delivery_System_Cashless.Repository.CustomerRespository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRespository customerRespository;
    public CustomerService(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }

    public CustomerResponseCreateDTO createCustomer(CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> ifExisting = customerRespository.findByCustomerEmailOrPhone(
                customerRequestDTO.getCustomerEmail(),
                customerRequestDTO.getCustomerPhoneNum());

        if (ifExisting.isPresent()) {
            throw new RuntimeException("account already existed");
        }

        Customer newCustomer = new Customer();
        newCustomer.setCustomerName(customerRequestDTO.getCustomerName());
        newCustomer.setCustomerPhone(customerRequestDTO.getCustomerPhoneNum());
        newCustomer.setCustomerEmail(customerRequestDTO.getCustomerEmail());
        newCustomer.setDeliveryAddress(customerRequestDTO.getDeliveryAddress());
        newCustomer.setCreatedAt(LocalDateTime.now());

        Customer createDone = customerRespository.save(newCustomer);
        return new CustomerResponseCreateDTO(
                createDone.getId(),
                createDone.getCustomerName(),
                createDone.getCustomerEmail(),
                createDone.getCustomerPhone(),
                createDone.getDeliveryAddress(),
                createDone.getCreatedAt()
        );
    }

    @Transactional
    public CustomerResponseUpdateDTO updateCustomer(Long id,
                                                    CustomerRequestDTO customerRequestDTO) {

        Customer foundCustomer = customerRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        foundCustomer.setCustomerName(customerRequestDTO.getCustomerName());
        foundCustomer.setCustomerEmail(customerRequestDTO.getCustomerEmail());
        foundCustomer.setCustomerPhone(customerRequestDTO.getCustomerPhoneNum());
        foundCustomer.setDeliveryAddress(customerRequestDTO.getDeliveryAddress());

        Customer savedCustomer = customerRespository.save(foundCustomer);

        return new CustomerResponseUpdateDTO(
                savedCustomer.getId(),
                savedCustomer.getCustomerName(),
                savedCustomer.getCustomerPhone(),
                savedCustomer.getCustomerEmail(),
                savedCustomer.getDeliveryAddress(),
                savedCustomer.getCreatedAt()
        );
    }

    public List<CustomerResponseCreateDTO> listOfCustomers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Customer> customerPage = customerRespository.findAll(pageable);

        return customerPage.getContent().stream()
                .map(customer -> new CustomerResponseCreateDTO(
                        customer.getId(),
                        customer.getCustomerName(),
                        customer.getCustomerEmail(),
                        customer.getCustomerPhone(),
                        customer.getDeliveryAddress(),
                        customer.getCreatedAt()
                )).collect(Collectors.toList());
    }
}
