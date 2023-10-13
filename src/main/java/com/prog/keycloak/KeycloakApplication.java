package com.prog.keycloak;

import com.prog.keycloak.entity.Employee;
import com.prog.keycloak.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/employees")
public class KeycloakApplication {

	@Autowired
	private EmployeeService service;

	@GetMapping("/{employeeId}")
	@RolesAllowed("user")
	public ResponseEntity<Employee> getEmployee(@PathVariable int employeeId) {
		return ResponseEntity.ok(service.getEmployee(employeeId));
	}

	@GetMapping
	@RolesAllowed("admin")
	public ResponseEntity<List<Employee>> findALlEmployees() {
		return ResponseEntity.ok(service.getAllEmployees());
	}

	public static void main(String[] args) {
		SpringApplication.run(KeycloakApplication.class, args);
	}

}
