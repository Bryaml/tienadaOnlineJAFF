package com.jaff.tiendaOnline.Service.costumer;

import com.jaff.tiendaOnline.Controller.AuthController.CustomerDTO;
import com.jaff.tiendaOnline.Entity.Address;
import com.jaff.tiendaOnline.Entity.Customer;
import com.jaff.tiendaOnline.Repository.AddressRepository;
import com.jaff.tiendaOnline.Repository.CustomerRepository;
import com.jaff.tiendaOnline.Service.EmailService.EmailService;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {
    private final EmailService emailService;
    private final CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerService(EmailService emailService, CustomerRepository customerRepository) {
        this.emailService = emailService;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Customer registerCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setName(customerDTO.getName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPassword(passwordEncoder.encode(customerDTO.getPassword())); // Encriptar la contraseña
        customer.setPhone(customerDTO.getPhone());
        customer.setRole("USER"); // Asignar un rol por defecto

        // Guardar la información del cliente
        Customer savedCustomer = customerRepository.save(customer);

        // Guardar la dirección del cliente
        Address address = new Address();
        address.setStreetNumber(customerDTO.getStreetNumber());
        address.setCp(customerDTO.getCp());
        address.setState(customerDTO.getState());
        address.setCity(customerDTO.getCity());
        address.setVillage(customerDTO.getVillage());
        address.setCustomer(savedCustomer);

        addressRepository.save(address);

        return savedCustomer;
    }


    public void forgetPassword(String email) {
        // Busca al cliente por su correo electrónico
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(email);
        if (!optionalCustomer.isPresent()) {
            throw new IllegalArgumentException("No se encontró ningún cliente con el correo electrónico " + email);
        }

        Customer customer = optionalCustomer.get();

        // Genera una nueva contraseña
        String newPassword = generateNewPassword();

        // Asigna la nueva contraseña al cliente y guárdalo
        customer.setPassword(passwordEncoder.encode(newPassword));
        customerRepository.save(customer);

        // Envía un correo electrónico al cliente con la nueva contraseña
        emailService.sendNewPasswordEmail(customer, newPassword);
    }

    private String generateNewPassword() {

        return RandomStringUtils.randomAlphanumeric(8);
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        customer.setName(updatedCustomer.getName());
        customer.setLastName(updatedCustomer.getLastName());
        customer.setPassword(updatedCustomer.getPassword());
        customer.setPhone(updatedCustomer.getPassword());

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + customerId));
        customerRepository.delete(customer);
    }

}
