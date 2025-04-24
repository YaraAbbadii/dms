package org.example.database_management.database.controller;

import lombok.RequiredArgsConstructor;
import org.example.database_management.database.DTO.EmployeeDTO;
import org.example.database_management.database.infrastructure.data.GeneralResponse;
import org.example.database_management.database.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping("/all")
    public GeneralResponse getAll() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/create")
    public GeneralResponse createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @PutMapping("/{id}")
    public GeneralResponse updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
