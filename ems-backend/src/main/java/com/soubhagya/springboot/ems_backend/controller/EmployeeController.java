package com.soubhagya.springboot.ems_backend.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soubhagya.springboot.ems_backend.dto.EmployeeDto;
import com.soubhagya.springboot.ems_backend.service.EmployeeService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
@Slf4j
public class EmployeeController {

	private EmployeeService employeeService;

	// Build Add Employee REST API
	//Will update the logger
	@PostMapping
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);

	}
	
	//Build find Employee By Given Id REST API 
	@GetMapping("{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
		EmployeeDto employeeDto =employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(employeeDto);
	}
	
	//Get All Employees REST API
	@GetMapping
	public ResponseEntity<List<EmployeeDto>> findAllEmployees(){
		List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
		return ResponseEntity.ok(allEmployees);
	}
	
	//Update Employee Details
	@PutMapping("{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeid,@RequestBody EmployeeDto updatedEmployee){
		EmployeeDto updatedEmployeedetails =employeeService.updateEmployee(employeeid, updatedEmployee);
		return ResponseEntity.ok(updatedEmployeedetails);
		
	}
	
	//Build DELETE REST API
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployeeById(employeeId);
		return ResponseEntity.ok("Employee with the given id deleted successfully");
	}

}
