package com.example.dms.service;


import com.example.dms.DTO.AddressDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.entity.AddressEntity;
import com.example.dms.entity.EmployeeEntity;
import com.example.dms.exception.ResourceNotFoundException;
import com.example.dms.mapper.AddressMapper;
import com.example.dms.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;


    public GeneralResponse getAllAddresses() {
        return new GeneralResponse().success(addressRepository.findAll());
    }

    public GeneralResponse getAddressById(Long id) {
        AddressEntity addressEntity = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        AddressDTO addressDTO = AddressMapper.toAddressDTO(addressEntity);
        return new GeneralResponse().success(addressDTO);
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
        AddressEntity address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        addressRepository.delete(address);
    }
}