package com.cognizant.controller;

import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.model.EmployeeRequest;
import com.cognizant.model.EmployeeResponse;
import com.cognizant.service.EmployeeService;

@RestController
@RequestMapping("api")
public class EmployeeController {

	
	private EmployeeService employeeService;
	@Autowired
	public EmployeeController(final EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	@GetMapping("employees")
	public ResponseEntity<?> handleGetAllEmployees(){
		List<EmployeeResponse> responseList=employeeService.getEmployeeResponseModels();
		ResponseEntity<List<EmployeeResponse>> responseEntity=null;
		if(!responseList.isEmpty()) {
			responseEntity=new ResponseEntity<List<EmployeeResponse>>(responseList,HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	@GetMapping("employees/{empId}")
	public ResponseEntity<?> handleGetEmployeeByPathParam(@PathVariable("empId")int empId){
		EmployeeResponse response=employeeService.getEmployeeResponseModelById(empId);
		ResponseEntity<EmployeeResponse> responseEntity=null;
		if(response.getEmpId()!=0) {
			responseEntity=new ResponseEntity<EmployeeResponse>(response,HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	@GetMapping("employeesRequestParam")
	public ResponseEntity<?> handleGetEmployeeByRequestParam(@RequestParam("empId")int empId){
		EmployeeResponse response=employeeService.getEmployeeResponseModelById(empId);
		ResponseEntity<EmployeeResponse> responseEntity=null;
		
		EmployeeRequest employeeRequest=new EmployeeRequest();
		
		
	
		
		if(response.getEmpId()!=0) {
			responseEntity=new ResponseEntity<EmployeeResponse>(response,HttpStatus.OK);
		}else {
			responseEntity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	@GetMapping("employeesMatrixParam/{employee}")
	public ResponseEntity<?> handleGetEmployeeByMatrixParam(@MatrixVariable Map<String,String> employee){
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	@PostMapping("employees")
	public ResponseEntity<?> persistEmployee(@RequestBody EmployeeRequest employeeRequest){
		String result=employeeService.persistEmployeeRequest(employeeRequest);
		if(result.equals("success")) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@PatchMapping("employees/{empId}/{newSalary}")
	public ResponseEntity<?> handleUpdateEmployeeSalary(@PathVariable("empId")int empId,@PathVariable("newSalary")int newSalary){
		String result=employeeService.updateEmployeeRequestSalary(empId, newSalary);
		if(result.equals("success")) {
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}
	@DeleteMapping("employees/{empId}")
	public ResponseEntity<?> handleDeleteEmployee(@PathVariable("empId")int empId){
		String result=employeeService.deleteEmployeeRequest(empId);
		if(result.equals("success")) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
}
