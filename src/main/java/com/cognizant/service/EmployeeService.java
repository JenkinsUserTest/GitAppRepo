package com.cognizant.service;

import java.util.List;

import com.cognizant.model.EmployeeRequest;
import com.cognizant.model.EmployeeResponse;

public interface EmployeeService {
	
	List<EmployeeResponse>getEmployeeResponseModels();
	EmployeeResponse getEmployeeResponseModelById(int empId);
	String persistEmployeeRequest(EmployeeRequest employeeRequest);
	String updateEmployeeRequestSalary(int empId ,double newSalary);
	String deleteEmployeeRequest(int empId);

}
