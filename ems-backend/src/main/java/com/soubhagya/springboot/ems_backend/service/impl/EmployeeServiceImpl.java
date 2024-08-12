package com.soubhagya.springboot.ems_backend.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.soubhagya.springboot.ems_backend.dto.EmployeeDto;
import com.soubhagya.springboot.ems_backend.entity.Employee;
import com.soubhagya.springboot.ems_backend.exception.ResourceNotFoundException;
import com.soubhagya.springboot.ems_backend.mapper.EmployeeMapper;
import com.soubhagya.springboot.ems_backend.repository.EmployeeRepository;
import com.soubhagya.springboot.ems_backend.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee with Given Id Doesn't exist::" + employeeId));

		return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
				.collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with the given id:" + employeeId));
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		
		Employee updatedEmployeenew =employeeRepository.save(employee);

		return EmployeeMapper.mapToEmployeeDto(updatedEmployeenew);
	}

	@Override
	public void deleteEmployeeById(Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with the given id:" + employeeId));
		employeeRepository.deleteById(employeeId);
	}

}
