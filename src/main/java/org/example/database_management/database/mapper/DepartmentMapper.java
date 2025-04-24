package org.example.database_management.database.mapper;

import org.example.database_management.database.DTO.DepartmentDTO;
import org.example.database_management.database.entity.DepartmentEntity;

public class DepartmentMapper {

    public static DepartmentDTO toDepartmentDTO(DepartmentEntity departmentEntity) {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(departmentEntity.getId());
        departmentDTO.setName(departmentEntity.getName());
        departmentDTO.setLocation(departmentEntity.getLocation());

        return departmentDTO;

    }

    public static DepartmentEntity toDepartmentEntity(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity = new DepartmentEntity();
        departmentEntity.setId(departmentDTO.getId());
        departmentEntity.setName(departmentDTO.getName());
        departmentEntity.setLocation(departmentDTO.getLocation());

        return departmentEntity;
    }
}
