package org.example.database_management.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "DEPARTMENT")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "department_sequence")
    @SequenceGenerator(name = "department_sequence", sequenceName = "department_sequence", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "LOCATION")
    private String location;


    @OneToMany(mappedBy = "departmentEntity")
    private List<EmployeeEntity> employeeEntities;

}
