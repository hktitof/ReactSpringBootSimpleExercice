package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Employe;

@Repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {

}


