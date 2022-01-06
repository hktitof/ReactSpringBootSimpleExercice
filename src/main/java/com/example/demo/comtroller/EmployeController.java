package com.example.demo.comtroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.dao.EmployeRepository;
import com.example.demo.entities.Employe;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class EmployeController {

    @Autowired
    private EmployeRepository employeRepository;
    
    // get All employes
    @GetMapping("/employes")
    public List<Employe> getAllEmployes(){
        return employeRepository.findAll();
    }
    // create employe rest Api
    @PostMapping("/employes")
    public Employe createEmploye(@RequestBody Employe employe) {
        return employeRepository.save(employe);
    }

    // Get employe By Id rest api
    @GetMapping("/employes/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id){
        Employe employe = employeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("L'employé n'existe pas avec cet identifiant"+ id));

        return ResponseEntity.ok(employe);
    }
    // Update employe By Id rest api
    @PutMapping("/employes/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id,@RequestBody Employe employeDetails){
        Employe employe = employeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("L'employé n'existe pas avec cet identifiant"+ id));
        employe.setFirstName(employeDetails.getFirstName());
        employe.setLastName(employeDetails.getLastName());
        employe.setEmailId(employeDetails.getEmailId());

        Employe updateEmploye = employeRepository.save(employe);

        return ResponseEntity.ok(updateEmploye);

    }
 // delete employe rest api
    @DeleteMapping("/employes/{id}")
    public ResponseEntity <Map<String,Boolean>> deleteEmploye(@PathVariable Long id){
        Employe employe = employeRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("L'employé n'existe pas avec cet identifiant"+ id));

        employeRepository.delete(employe);

        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}


