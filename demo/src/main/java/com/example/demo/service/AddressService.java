package com.example.demo.service;


import com.example.demo.entity.Address;
import com.example.demo.repository.AddressRepo;
import com.example.demo.responce.AddressResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private ModelMapper mapper;

    public AddressResponse findAddressByEmployeeId(int employeeId) {
        Optional<Address> addressByEmployeeId = addressRepo.findAddressByEmployeeId(employeeId);
        AddressResponse addressResponse = mapper.map(addressByEmployeeId, AddressResponse.class);
        return addressResponse;
    }

    public String addAddress(Address address) {
        addressRepo.save(address);
        return "Address add successfully";
    }
}
