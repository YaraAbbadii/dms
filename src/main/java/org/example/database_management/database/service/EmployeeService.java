package org.example.database_management.database.service;

import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.AddressDTO;
import org.example.database_management.database.DTO.EmployeeDTO;
import org.example.database_management.database.entity.AddressEntity;
import org.example.database_management.database.entity.DepartmentEntity;
import org.example.database_management.database.entity.EmployeeEntity;
import org.example.database_management.database.entity.ProjectEntity;
import org.example.database_management.database.exception.ResourceNotFoundException;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.mapper.AddressMapper;
import org.example.database_management.database.mapper.DepartmentMapper;
import org.example.database_management.database.mapper.EmployeeMapper;
import org.example.database_management.database.mapper.ProjectMapper;
import org.example.database_management.database.repository.AddressRepository;
import org.example.database_management.database.repository.DepartmentRepository;
import org.example.database_management.database.repository.EmployeeRepository;
import org.example.database_management.database.repository.ProjectRepository;
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

//    public GeneralResponse saveOrUpdate(Employee employee) {
//
//        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
//            Department department = departmentRepository.findById(employee.getDepartment().getId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + employee.getDepartment().getId()));
//            employee.setDepartment(department);
//        }
//
//        if (employee.getProjects() != null && !employee.getProjects().isEmpty()) {
//            Set<Project> projects = employee.getProjects().stream()
//                    .map(project -> projectRepository.findById(project.getId())
//                            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + project.getId())))
//                    .collect(Collectors.toSet());
//            employee.setProjects(projects);
//        }
//
//        return new GeneralResponse(employeeRepository.save(employee));
//    }

    public GeneralResponse getEmployeeById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        return new GeneralResponse().success(employeeEntity);
    }

    public GeneralResponse getAllEmployees() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = EmployeeMapper.toEmployeeDTOList(employeeEntityList);
        return new GeneralResponse().success(employeeDTOList);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


    public GeneralResponse createEmployee(EmployeeDTO employeeDTO) {
        AddressEntity addressEntity = AddressMapper.toAddressEntity(employeeDTO.getAddressDTO());
        addressRepository.saveAndFlush(addressEntity);
        DepartmentEntity departmentEntity = DepartmentMapper.toDepartmentEntity(employeeDTO.getDepartmentDTO());
        departmentRepository.saveAndFlush(departmentEntity);
        ProjectEntity projectEntity = ProjectMapper.toProjectEntity(employeeDTO.getProjectDTO());
        projectRepository.saveAndFlush(projectEntity);
        EmployeeEntity employeeEntity = EmployeeMapper.toEmployeeEntity(employeeDTO);
        employeeEntity.setAddressEntity(addressEntity);
        employeeEntity.setDepartmentEntity(departmentEntity);
        employeeEntity.setProjectEntities(Set.of(projectEntity));
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

//        if (updatedEmployee.getDepartment() != null && updatedEmployee.getDepartment().getId() != null) {
//            Department department = departmentRepository.findById(updatedEmployee.getDepartment().getId())
//                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + updatedEmployee.getDepartment().getId()));
//            existingEmployee.setDepartment(department);
//        }
//
//        if (updatedEmployee.getProjects() != null && !updatedEmployee.getProjects().isEmpty()) {
//            Set<Project> projects = updatedEmployee.getProjects().stream()
//                    .map(project -> projectRepository.findById(project.getId())
//                            .orElseThrow(() -> new ResourceNotFoundException("Project not found with id " + project.getId())))
//                    .collect(Collectors.toSet());
//            existingEmployee.setProjects(projects);
//        }

}