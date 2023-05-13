package com.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.java.entity.Employee;
import com.java.repo.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {

		return employeeRepository.findAll();
	}

	public Optional<Employee> getEmployee(int id) {

		return employeeRepository.findById(id);
	}

	public String deleteEmployee(int id) {

		employeeRepository.deleteById(id);
		return "deleted successfully";
	}

	public Employee updateEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

}
