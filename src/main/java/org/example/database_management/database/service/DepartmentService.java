package org.example.database_management.database.service;


import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.AddressDTO;
import org.example.database_management.database.DTO.DepartmentDTO;
import org.example.database_management.database.entity.AddressEntity;
import org.example.database_management.database.entity.DepartmentEntity;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.mapper.DepartmentMapper;
import org.example.database_management.database.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public GeneralResponse getAllDepartments() {
        return new GeneralResponse().success(departmentRepository.findAll());
    }

    public GeneralResponse createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity entity = DepartmentMapper.toDepartmentEntity(departmentDTO);
        return new GeneralResponse().success(departmentRepository.save(entity));
    }

    public GeneralResponse updateDepartment(Long id, DepartmentDTO departmentDTO) {
        return departmentRepository.findById(id).map(departmentDTODetails -> {
            departmentDTODetails.setName(departmentDTO.getName());
            departmentDTODetails.setLocation(departmentDTO.getLocation());
            return new GeneralResponse().success(departmentRepository.save(departmentDTODetails));
        }).orElseThrow(() -> new RuntimeException("Department not found"));
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}