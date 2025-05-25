package com.example.dms.repository;

import com.example.dms.entity.EmployeeEntity;
import com.example.dms.projection.EmployeeProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, JpaSpecificationExecutor<EmployeeEntity> {

    Optional<EmployeeEntity> findByEmail(String email);

    @Query(value = "SELECT e.employeeId as ID, e.name as NAME, e.email as EMAIL FROM EmployeeEntity e")
    List<EmployeeProjection> getEmployee();

}
