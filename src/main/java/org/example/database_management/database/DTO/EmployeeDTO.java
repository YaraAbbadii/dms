package org.example.database_management.database.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date hireDate;
    private String salary;

    private AddressDTO addressDTO;
    private DepartmentDTO departmentDTO;
    private ProjectDTO projectDTO;
}
