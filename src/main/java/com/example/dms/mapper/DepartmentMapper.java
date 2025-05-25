package com.example.dms.mapper;

import com.example.dms.DTO.DepartmentDTO;
import com.example.dms.entity.DepartmentEntity;

import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper {

    public static DepartmentDTO toDepartmentDTO(DepartmentEntity departmentEntity) {
        if (departmentEntity == null) {
            return null;
        }
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(departmentEntity.getId());
        departmentDTO.setName(departmentEntity.getName());
        departmentDTO.setLocation(departmentEntity.getLocation());
        return departmentDTO;
    }


    public static DepartmentEntity toDepartmentEntity(DepartmentDTO departmentDTO) {
        if (departmentDTO == null) {
            return null;
        }
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(departmentDTO.getId());
        departmentEntity.setName(departmentDTO.getName());
        departmentEntity.setLocation(departmentDTO.getLocation());
        return departmentEntity;
    }
}
