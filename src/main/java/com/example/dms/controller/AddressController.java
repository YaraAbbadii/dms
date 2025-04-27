package com.example.dms.controller;


import com.example.dms.DTO.AddressDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.service.AddressService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("get/{id}")
    public GeneralResponse getAddressById(@PathVariable Long id) {
        return addressService.getAddressById(id);
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
