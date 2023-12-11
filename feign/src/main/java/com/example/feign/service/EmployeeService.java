package com.example.feign.service;

import com.example.feign.entity.Employee;
import com.example.feign.feignclient.AddressClient;
import com.example.feign.repository.EmployeeRepo;
import com.example.feign.response.AddressResponse;
import com.example.feign.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;


    @Autowired
    private AddressClient addressClient;

    public String addEmployee(Employee employee) {
        employeeRepo.save(employee);
        return "employee added successfully";
    }

    public EmployeeResponse getEmployeeById(int id) {

        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);

        // Using FeignClient
        ResponseEntity<AddressResponse> addressResponse = addressClient.getAddressByEmployeeId(id);
        employeeResponse.setAddressResponse(addressResponse.getBody());

        return employeeResponse;
    }

}
