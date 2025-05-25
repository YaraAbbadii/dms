package com.example.dms.mapper;


import com.example.dms.DTO.AddressDTO;
import com.example.dms.entity.AddressEntity;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper {

    public static AddressDTO toAddressDTO(AddressEntity addressEntity) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(addressEntity.getId());
        addressDTO.setCity(addressEntity.getCity());
        addressDTO.setStreet(addressEntity.getStreet());
        addressDTO.setZipCode(addressEntity.getZipCode());

        return addressDTO;
    }

    public static AddressEntity toAddressEntity(AddressDTO addressDTO) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(addressDTO.getId());
        addressEntity.setCity(addressDTO.getCity());
        addressEntity.setStreet(addressDTO.getStreet());
        addressEntity.setZipCode(addressDTO.getZipCode());

        return addressEntity;
    }
}
