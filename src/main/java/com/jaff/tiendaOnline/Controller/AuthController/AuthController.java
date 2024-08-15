package com.jaff.tiendaOnline.Controller.AuthController;

import com.jaff.tiendaOnline.Entity.Customer;
import com.jaff.tiendaOnline.Repository.CustomerRepository;

import com.jaff.tiendaOnline.Security.Jwt.JwtResponse;
import com.jaff.tiendaOnline.Security.Jwt.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class AuthController {

    private final CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public AuthController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (passwordEncoder.matches(password, customer.getPassword())) {
                final UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                final String token = jwtTokenUtil.generateToken(userDetails);
                CustomerDTO customerDTO = new CustomerDTO(customer);
                System.out.println("CustomerDTO: " + customerDTO);
                return ResponseEntity.ok(new JwtResponse(token, "Login exitoso", customerDTO));
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
    }
}
