package com.example.demo.repository;

import com.example.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AddressRepo extends JpaRepository<Address, Integer> {
    @Query(
            nativeQuery = true,
            value
                    = "SELECT ea.id, ea.city, ea.state FROM addressDb.address ea join userDb.employee e on e.id = ea.id where ea.id=:employeeId")
    Optional<Address> findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
