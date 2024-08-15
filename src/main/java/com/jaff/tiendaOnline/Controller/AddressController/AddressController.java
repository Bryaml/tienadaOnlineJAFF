package com.jaff.tiendaOnline.Controller.AddressController;

import com.jaff.tiendaOnline.Entity.Address;
import com.jaff.tiendaOnline.Repository.AddressRepository;
import com.jaff.tiendaOnline.Service.Product.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/{customerId}")
    public ResponseEntity<List<Address>> getAddressesByCustomerId(@PathVariable Long customerId) {
        List<Address> addresses = addressRepository.findByCustomerCustomerId(customerId);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        Address savedAddress = addressRepository.save(address);
        return ResponseEntity.ok(savedAddress);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @RequestBody Address addressDetails) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId));

        address.setStreetNumber(addressDetails.getStreetNumber());
        address.setCp(addressDetails.getCp());
        address.setState(addressDetails.getState());
        address.setCity(addressDetails.getCity());
        address.setVillage(addressDetails.getVillage());

        Address updatedAddress = addressRepository.save(address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + addressId));

        addressRepository.delete(address);
        return ResponseEntity.noContent().build();
    }
}
