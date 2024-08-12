package com.soubhagya.springboot.ems_backend.service;

import java.util.List;

import com.soubhagya.springboot.ems_backend.dto.EmployeeDto;

public interface EmployeeService {
	
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	
	EmployeeDto getEmployeeById(Long employeeId);
	
	
	List<EmployeeDto> getAllEmployees();

	
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
	
	void deleteEmployeeById(Long employeeId);
}
