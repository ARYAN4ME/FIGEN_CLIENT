package com.example.feign.controller;

import com.example.feign.entity.Employee;
import com.example.feign.response.EmployeeResponse;
import com.example.feign.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    private ResponseEntity<String> addEmployee(@RequestBody()Employee employee){
        String emp = employeeService.addEmployee(employee);
        return ResponseEntity.status(HttpStatus.OK).body(emp);
    }

    @GetMapping("/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }
}
