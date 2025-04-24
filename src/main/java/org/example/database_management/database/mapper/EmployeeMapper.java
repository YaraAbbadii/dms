package org.example.database_management.database.mapper;

import org.example.database_management.database.DTO.AddressDTO;
import org.example.database_management.database.DTO.DepartmentDTO;
import org.example.database_management.database.DTO.EmployeeDTO;
import org.example.database_management.database.DTO.ProjectDTO;
import org.example.database_management.database.entity.EmployeeEntity;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper {

    public static EmployeeDTO toEmployeeDTO(EmployeeEntity employeeEntity) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employeeEntity.getEmployeeId());
        employeeDTO.setName(employeeEntity.getName());
        employeeDTO.setEmail(employeeEntity.getEmail());
        employeeDTO.setHireDate(employeeEntity.getHireDate());
        employeeDTO.setSalary(employeeEntity.getSalary());
        AddressDTO addressDTO = AddressMapper.toAddressDTO(employeeEntity.getAddressEntity());
        employeeDTO.setAddressDTO(addressDTO);
        DepartmentDTO departmentDTO = DepartmentMapper.toDepartmentDTO(employeeEntity.getDepartmentEntity());
        employeeDTO.setDepartmentDTO(departmentDTO);
        ProjectDTO projectDTO = ProjectMapper.toProjectDTO(employeeEntity.getProjectEntities().iterator().next());
        employeeDTO.setProjectDTO(projectDTO);
        return employeeDTO;
    }

    public static EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmployeeId(employeeDTO.getId());
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setHireDate(employeeDTO.getHireDate());
        employeeEntity.setSalary(employeeDTO.getSalary());
        return employeeEntity;
    }

    public static List<EmployeeDTO> toEmployeeDTOList(List<EmployeeEntity> employeeEntities) {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            EmployeeDTO employeeDTO = toEmployeeDTO(employeeEntity);
            employeeDTOList.add(employeeDTO);
        }
        return employeeDTOList;
    }
}
