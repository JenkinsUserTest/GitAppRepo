package com.cognizant.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//@Getter
//@Setter
//@NoArgsConstructor
@Data
public class EmployeeRequest {
	private int empId;
	private String empName;
	private double empSalary;
	private String empDesignation;
	
	
	
}
