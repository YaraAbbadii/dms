package org.example.database_management.database.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class DepartmentDTO {

    private Long id;
    private String name;
    private String location;
}
