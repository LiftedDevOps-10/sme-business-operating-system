package com.lifted.smebos.repository;

import com.lifted.smebos.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFullNameContainingIgnoreCase(String fullName);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByPhone(String phone);
}