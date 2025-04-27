package com.example.dms.controller;


import com.example.dms.DTO.DepartmentDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.service.DepartmentService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("get/{id}")
    public GeneralResponse getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/create")
    public GeneralResponse createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        if (departmentDTO == null) {
            return new GeneralResponse();
        }
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
