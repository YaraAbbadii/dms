package org.example.database_management.database.controller;


import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.AddressDTO;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.service.AddressService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/all")
    public GeneralResponse getAll() {
        return addressService.getAllAddresses();
    }

    @PostMapping("/create")
    public GeneralResponse createAddress(@RequestBody AddressDTO AddressDTO) {
        return addressService.createNewAddress(AddressDTO);
    }

    @PutMapping("/update/{id}")
    public GeneralResponse updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTODetails) {
        return addressService.updateAddress(id, addressDTODetails);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}
