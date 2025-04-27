package com.example.dms.controller;

import com.example.dms.DTO.EmployeeDTO;
import com.example.dms.data.GeneralResponse;
import com.example.dms.service.EmployeeService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/get_projection")
    public GeneralResponse getSomeData(){
        return employeeService.getSomeData();
    }

    @GetMapping("get/{id}")
    public GeneralResponse getEmployeeById(@PathVariable Long id){
        return employeeService.getEmployeeById(id);
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
