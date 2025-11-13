package com.example.Food_Delivery_System_Cashless.Controllers;

import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderCreateResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderDeleteResponseDTO;
import com.example.Food_Delivery_System_Cashless.DTOs.OrderDTO.OrderRequestDTO;
import com.example.Food_Delivery_System_Cashless.Services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "v1/orders")
public class OrderController {

    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping
    public ResponseEntity<OrderCreateResponseDTO> createOrder(
            @RequestBody OrderRequestDTO orderRequestDTO) {
        OrderCreateResponseDTO savedResult = orderService.createOrder(orderRequestDTO);
        return ResponseEntity.ok(savedResult);
    }

    @GetMapping(path = "/{id}/order-id")
    public ResponseEntity<OrderCreateResponseDTO> getOrderById(
            @PathVariable Long id) {
        OrderCreateResponseDTO getOrderId = orderService.getOrderById(id);
        return ResponseEntity.ok(getOrderId);
    }

    @GetMapping(path = "/{orderNumber}/order-number")
    public ResponseEntity<OrderCreateResponseDTO> getOrderByNumber(
            @PathVariable String orderNumber) {
        OrderCreateResponseDTO getOrderNumber = orderService.getOrderByNumber(orderNumber);
        return ResponseEntity.ok(getOrderNumber);
    }

    // @PreAuthorize("hasRole('ADMIN') or hasRole('RESTAURANT')")
    @PutMapping(path = "/{id}/updated-order")
    public ResponseEntity<OrderCreateResponseDTO> updateOrder(
            @PathVariable Long id,
            @RequestParam String status) {
        OrderCreateResponseDTO updatedOrder = orderService.updateOrder(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // @PreAuthorize("hasRole('ADMIN') or hasRole('RESTAURANT') or hasRole('CUSTOMER')")
    @PutMapping(path = "/{id}/cancel-order")
    public ResponseEntity<OrderCreateResponseDTO> cancelOrder(
            @PathVariable Long id,
            @RequestParam String cancelReason,
            @RequestParam String cancelledBy) {
        OrderCreateResponseDTO cancelledOrder = orderService.cancelOrder(id, cancelReason, cancelledBy);
        return ResponseEntity.ok(cancelledOrder);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(path = "/{id}/assign-rider/{riderId}")
    public ResponseEntity<OrderCreateResponseDTO> assignRider(
            @PathVariable Long id,
            @PathVariable Long riderId) {
        OrderCreateResponseDTO assignedRider = orderService.assignRider(id, riderId);
        return ResponseEntity.ok(assignedRider);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderDeleteResponseDTO> deleteOrder(@PathVariable Long id) {
        OrderDeleteResponseDTO response = orderService.deleteOrderByAdmin(id);
        return ResponseEntity.ok(response);
    }

    // @PreAuthorize("hasRole('ADMIN') or hasRole('RESTAURANT')")
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderCreateResponseDTO>> getOrdersByStatus(@PathVariable String status) {
        List<OrderCreateResponseDTO> orders = orderService.getOrderStatusByAdmin(status);
        return ResponseEntity.ok(orders);
    }

    // @PreAuthorize("hasRole('ADMIN') or (hasRole('CUSTOMER') and #customerId == principal.id)")
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderCreateResponseDTO>> getOrdersByCustomer(@PathVariable Long customerId) {
        List<OrderCreateResponseDTO> orders = orderService.getOrdersByCustomers(customerId);
        return ResponseEntity.ok(orders);
    }

    // @PreAuthorize("hasRole('ADMIN') or (hasRole('RESTAURANT') and #restaurantId == principal.restaurantId)")
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<OrderCreateResponseDTO>> getOrdersByRestaurant(@PathVariable Long restaurantId) {
        List<OrderCreateResponseDTO> orders = orderService.getOrdersByRestaurant(restaurantId);
        return ResponseEntity.ok(orders);
    }

}
