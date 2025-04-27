package com.example.dms.mapper;

import com.example.dms.DTO.AddressDTO;
import com.example.dms.DTO.DepartmentDTO;
import com.example.dms.DTO.EmployeeDTO;
import com.example.dms.DTO.ProjectDTO;
import com.example.dms.entity.AddressEntity;
import com.example.dms.entity.DepartmentEntity;
import com.example.dms.entity.EmployeeEntity;
import com.example.dms.entity.ProjectEntity;

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

        ProjectDTO projectDTO = ProjectMapper.toProjectDTO(employeeEntity.getProjectEntities().getFirst());
        employeeDTO.setProjectDTO(List.of(projectDTO));

        return employeeDTO;
    }

    public static EmployeeEntity toEmployeeEntity(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = new EmployeeEntity();

        employeeEntity.setEmployeeId(employeeDTO.getId());
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setEmail(employeeDTO.getEmail());
        employeeEntity.setHireDate(employeeDTO.getHireDate());
        employeeEntity.setSalary(employeeDTO.getSalary());

        AddressEntity addressEntity = AddressMapper.toAddressEntity(employeeDTO.getAddressDTO());
        employeeEntity.setAddressEntity(addressEntity);

        DepartmentEntity departmentEntity = DepartmentMapper.toDepartmentEntity(employeeDTO.getDepartmentDTO());
        employeeEntity.setDepartmentEntity(departmentEntity);


        List<ProjectEntity> projectEntityList = ProjectMapper.toProjectEntityList(employeeDTO.getProjectDTO());
        employeeEntity.setProjectEntities(projectEntityList);
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
