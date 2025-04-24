package org.example.database_management.database.service;


import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.AddressDTO;
import org.example.database_management.database.entity.AddressEntity;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.mapper.AddressMapper;
import org.example.database_management.database.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;


    public GeneralResponse getAllAddresses() {
        return new GeneralResponse().success(addressRepository.findAll());
    }

    public GeneralResponse createNewAddress(AddressDTO addressDTO) {
        AddressEntity entity = AddressMapper.toAddressEntity(addressDTO);
        return new GeneralResponse().success(addressRepository.save(entity));
    }

    public GeneralResponse updateAddress(Long id, AddressDTO updated) {
        return addressRepository.findById(id).map(addressDTO -> {
            addressDTO.setStreet(updated.getStreet());
            addressDTO.setCity(updated.getCity());
            addressDTO.setZipCode(updated.getZipCode());
            return new GeneralResponse().success(addressRepository.save(addressDTO));
        }).orElseThrow(() -> new RuntimeException("Address not found"));
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}