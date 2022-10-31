package com.example.demo.repository;


import com.example.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Cliente, Long> {
    //testyeete
}
