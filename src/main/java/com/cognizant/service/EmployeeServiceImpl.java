package com.cognizant.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.entities.Employee;
import com.cognizant.model.EmployeeRequest;
import com.cognizant.model.EmployeeResponse;
import com.cognizant.repositories.EmployeeRepository;

@Service("employeeServiceImpl")

public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	public List<EmployeeResponse> getEmployeeResponseModels() {
		Iterable<Employee> employeeList=employeeRepository.findAll();
		List<EmployeeResponse> employeeResponseList=new ArrayList<EmployeeResponse>();
		Iterator<Employee> iteratorOfEmployee=employeeList.iterator();
	    while(iteratorOfEmployee.hasNext()) {
	    	Employee employee=iteratorOfEmployee.next();
	    	EmployeeResponse employeeResponse=new EmployeeResponse();
			employeeResponse.setEmpId(employee.getEmpId());
			employeeResponse.setEmpName(employee.getEmpName().toUpperCase());
			employeeResponse.setEmpSalary(employee.getEmpSalary());
			employeeResponse.setEmpDesignation(employee.getEmpDesignation());
			employeeResponseList.add(employeeResponse);
	    }
		if(employeeResponseList.isEmpty()) {
			throw new RuntimeException("List is empty");
		}
		return employeeResponseList;
	}

	public EmployeeResponse getEmployeeResponseModelById(int empId) {
		    Optional<Employee> optionalOfEmployee=employeeRepository.findById(empId);
			EmployeeResponse employeeResponse=new EmployeeResponse();
		    if(optionalOfEmployee.isPresent()) {
		    	Employee employee=optionalOfEmployee.get();
			employeeResponse.setEmpId(employee.getEmpId());
			employeeResponse.setEmpName(employee.getEmpName().toUpperCase());
			employeeResponse.setEmpSalary(employee.getEmpSalary());
			employeeResponse.setEmpDesignation(employee.getEmpDesignation());
		    }
		
		   return employeeResponse;
	}

	public String persistEmployeeRequest(EmployeeRequest employeeRequest) {
		if(employeeRequest.getEmpId()==0) {
			throw new RuntimeException("Invalid Employee Id");
		}
		Employee employee=new Employee();
		employee.setEmpId(employeeRequest.getEmpId());
		employee.setEmpName(employeeRequest.getEmpName());
		//int c=1/0;
		employee.setEmpSalary(employeeRequest.getEmpSalary());
		employee.setEmpDesignation(employeeRequest.getEmpDesignation());
		Employee employeeCreated=employeeRepository.save(employee);
		if(employeeCreated!=null)
			return "success";
		else
		return "fail";
	}

	public String updateEmployeeRequestSalary(int empId, double newSalary) {
		
		Optional<Employee> optionalOfEmployee=employeeRepository.findById(empId);
		Employee employeeUpdated=null;
		if(optionalOfEmployee.isPresent()) {
	    	Employee employee=optionalOfEmployee.get();
	    	employee.setEmpSalary(newSalary);
		employeeUpdated=employeeRepository.save(employee);
		}
		if(employeeUpdated!=null)
			return "success";
		else
		return "fail";
	}

	public String deleteEmployeeRequest(int empId) {
		if(empId==0) {
			throw new RuntimeException("Emp Id cannot be negative");
		}
		employeeRepository.deleteById(empId);
		return "success";
	}

}
