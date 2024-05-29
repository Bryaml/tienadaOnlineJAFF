package com.jaff.tiendaOnline.Controller.Costumer;

import com.jaff.tiendaOnline.Entity.Customer;
import com.jaff.tiendaOnline.Service.costumer.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        System.out.println("Registering customer: " + customer);
        Customer savedCustomer = customerService.registerCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    @PostMapping("/forgetPassword")
    public ResponseEntity<Void> forgetPassword(@RequestBody ForgetPasswordRequest request) {
        customerService.forgetPassword(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId:\\d+}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{customerId:\\d+}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(customerId, updatedCustomer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{customerId:\\d+}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok().build();
    }
}
