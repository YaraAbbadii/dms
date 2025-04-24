package org.example.database_management.database.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "PROJECT")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "project_sequence")
    @SequenceGenerator(name = "project_sequence", sequenceName = "project_sequence", allocationSize = 1)
    @Column(name = "PROJECT_ID")
    private Long projectId;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "BUDGET")
    private String budget;
    @Column(name = "DEADLINE")
    private Date deadline;

    @ManyToMany(mappedBy = "projectEntities")
    private Set<EmployeeEntity> employeeEntities;
}
