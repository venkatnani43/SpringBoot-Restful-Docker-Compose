package com.java.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.entity.Employee;
import com.java.exception.ResourceNotFoundException;
import com.java.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService;

	@PostMapping("/save")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {

		return ResponseEntity.ok().body(employeeService.saveEmployee(employee));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getEmployees() {

		return ResponseEntity.ok(employeeService.getEmployees());
	}

	@GetMapping("/get")
	public ResponseEntity<Employee> getEmployee(@RequestParam("empid") int empid) throws ResourceNotFoundException {
		
		 Employee employee = employeeService.getEmployee(empid)
			        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + empid));
			    
		return ResponseEntity.ok().body(employee);
	}

	@DeleteMapping("/delete/{empid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable int empid) {

		return ResponseEntity.ok(employeeService.deleteEmployee(empid));
	}

	@PatchMapping("/update/{empid}/{salary}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "empid") int empid, @PathVariable int salary) throws ResourceNotFoundException {
		 Employee e = employeeService.getEmployee(empid)
			        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + empid));
		
		e.setSalary(salary);

		return new ResponseEntity<Employee>(employeeService.updateEmployee(e), HttpStatus.OK);
	}

	@PutMapping("/update/{empid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "empid") int empid,
			@RequestBody Employee employee) throws ResourceNotFoundException {

		 Employee e = employeeService.getEmployee(empid)
			        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + empid));
		
		e.setEmpname(employee.getEmpname());
		e.setSalary(employee.getSalary());

		return new ResponseEntity<Employee>(employeeService.updateEmployee(e), HttpStatus.OK);
	}

}
