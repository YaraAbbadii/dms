package com.example.dms.DTO;

import com.example.dms.customAnnotation.ArabicLettersOnly;
import com.example.dms.customAnnotation.CharactersOnly;
import com.example.dms.customAnnotation.Email;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class EmployeeDTO {

    private Long id;
    @NotNull
    @NotBlank
    @ArabicLettersOnly
//    @CharactersOnly
    private String name;
    @NotNull
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull
    private Date hireDate;
    @NotNull
    @NotBlank
    private String salary;

    @NotNull
    private AddressDTO addressDTO;
    private DepartmentDTO departmentDTO;
    private List<ProjectDTO> projectDTOList;


}
