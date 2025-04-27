package com.example.dms.service;


import com.example.dms.DTO.DepartmentDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.entity.DepartmentEntity;
import com.example.dms.exception.ResourceNotFoundException;
import com.example.dms.mapper.DepartmentMapper;
import com.example.dms.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public GeneralResponse getAllDepartments() {
        return new GeneralResponse().success(departmentRepository.findAll());
    }

    public GeneralResponse getDepartmentById(Long id) {
        DepartmentEntity departmentEntity = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        DepartmentDTO departmentDTO = DepartmentMapper.toDepartmentDTO(departmentEntity);
        return new GeneralResponse().success(departmentDTO);
    }

    public GeneralResponse createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity entity = DepartmentMapper.toDepartmentEntity(departmentDTO);
        if (entity == null) {
            throw new IllegalArgumentException("Department data is invalid");
        }
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
        DepartmentEntity department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        departmentRepository.delete(department);
    }
}