package org.example.database_management.database.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ADDRESS")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "STREET")
    private String street;
    @Column(name = "CITY")
    private String city;
    @Column(name = "ZIP_CODE")
    private String zipCode;


    @OneToOne(mappedBy = "addressEntity")
    private EmployeeEntity employeeEntity;
}