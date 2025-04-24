package org.example.database_management.database.specification;

import jakarta.persistence.criteria.Join;
import org.example.database_management.database.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;


public class EmployeeSpecification {

    //filter by value range, SQL: WHERE salary BETWEEN x AND y
    public static Specification<EmployeeEntity> salaryBetween(Double minSalary, Double maxSalary) {
        return (root, query, cb) -> cb.between(root.get("salary"), minSalary, maxSalary);
    }

    //    filter by date range, SQL: WHERE hire_date BETWEEN x AND y
    public static Specification<EmployeeEntity> hireDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, cb) -> cb.between(root.get("hireDate"), startDate, endDate);
    }

    //    filter by joined entity, SQL: JOIN department ON ... WHERE department.name = ?
    public static Specification<EmployeeEntity> hasDepartmentName(String departmentName) {
        return (root, query, cb) -> {
            Join<Object, Object> department = root.join("department");
            return cb.equal(department.get("name"), departmentName);
        };
    }

    // filter by relationship, SQL: JOIN projects ON ... WHERE projects.id = ?
    public static Specification<EmployeeEntity> involvedInProject(Long projectId) {
        return (root, query, cb) -> {
            Join<Object, Object> projects = root.join("projects");
            return cb.equal(projects.get("id"), projectId);
        };
    }
}
