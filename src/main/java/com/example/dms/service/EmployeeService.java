package com.example.dms.service;

import com.example.dms.DTO.EmployeeDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.entity.AddressEntity;
import com.example.dms.entity.DepartmentEntity;
import com.example.dms.entity.EmployeeEntity;
import com.example.dms.entity.ProjectEntity;
import com.example.dms.exception.ResourceNotFoundException;
import com.example.dms.mapper.AddressMapper;
import com.example.dms.mapper.DepartmentMapper;
import com.example.dms.mapper.EmployeeMapper;
import com.example.dms.mapper.ProjectMapper;
import com.example.dms.projection.EmployeeProjection;
import com.example.dms.repository.AddressRepository;
import com.example.dms.repository.DepartmentRepository;
import com.example.dms.repository.EmployeeRepository;
import com.example.dms.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectRepository projectRepository;
    private final AddressRepository addressRepository;


    public GeneralResponse getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        EmployeeDTO employeeDTO = EmployeeMapper.toEmployeeDTO(employeeEntity);
        return new GeneralResponse().success(employeeDTO);
    }

    public GeneralResponse getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = EmployeeMapper.toEmployeeDTOList(employeeEntityList);
        return new GeneralResponse().success(employeeDTOList);
    }

    public void deleteEmployee(Long id) {
        EmployeeEntity employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        employeeRepository.delete(employee);
    }


    public GeneralResponse createEmployee(EmployeeDTO employeeDTO) {

        AddressEntity addressEntity = AddressMapper.toAddressEntity(employeeDTO.getAddressDTO());
        addressRepository.saveAndFlush(addressEntity);

        DepartmentEntity departmentEntity = DepartmentMapper.toDepartmentEntity(employeeDTO.getDepartmentDTO());
        departmentRepository.saveAndFlush(departmentEntity);

//        ProjectEntity projectEntity = ProjectMapper.toProjectEntity(employeeDTO.getProjectDTO());
//        projectRepository.saveAndFlush(projectEntity);

        List<ProjectEntity> projectEntityList = ProjectMapper.toProjectEntityList(employeeDTO.getProjectDTO());
        projectRepository.saveAllAndFlush(projectEntityList);

        EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(employeeDTO);

        employeeEntity.setAddressEntity(addressEntity);
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeEntity.setProjectEntities(projectEntityList);

        EmployeeEntity savedEmployeeEntity = employeeRepository.saveAndFlush(employeeEntity);
        EmployeeDTO employeeResponse = EmployeeMapper.toEmployeeDTO(savedEmployeeEntity);

        return new GeneralResponse().success(employeeResponse);
    }

    public GeneralResponse updateEmployee(Long id, EmployeeDTO updatedEmployeeDTO) {
        return employeeRepository.findById(id).map(updated -> {
                    updated.setName(updatedEmployeeDTO.getName());
                    updated.setEmail(updatedEmployeeDTO.getEmail());
                    updated.setHireDate(updatedEmployeeDTO.getHireDate());
                    updated.setSalary(updatedEmployeeDTO.getSalary());
                    return new GeneralResponse().success(employeeRepository.save(updated));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
    }

    public GeneralResponse getSomeData() {
        List<EmployeeProjection> employeeProjection = employeeRepository.getEmployee();
        System.out.println(employeeProjection);
        return null;
    }

}