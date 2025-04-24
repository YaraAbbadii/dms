package org.example.database_management.database.controller;


import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.DepartmentDTO;
import org.example.database_management.database.entity.DepartmentEntity;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/all")
    public GeneralResponse getAll() {
        return departmentService.getAllDepartments();
    }

    @PostMapping("/create")
    public GeneralResponse createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }

    @PutMapping("/{id}")
    public GeneralResponse updateDepartment(@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO) {
        return departmentService.updateDepartment(id, departmentDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
