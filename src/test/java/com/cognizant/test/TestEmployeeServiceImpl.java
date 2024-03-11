package com.cognizant.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.entities.Employee;
import com.cognizant.model.EmployeeRequest;
import com.cognizant.model.EmployeeResponse;
import com.cognizant.repositories.EmployeeRepository;
import com.cognizant.service.EmployeeServiceImpl;

class TestEmployeeServiceImpl {
	@Mock
	private EmployeeRepository employeeRepository;
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testUpdateEmployeeRequestSalary_Positive() {
		try {
		Employee employee=new Employee();
		employee.setEmpId(1001);
		employee.setEmpName("sabbir");
		employee.setEmpSalary(34000);
		employee.setEmpDesignation("Trainer");
		Optional<Employee> optionalOfEmployee=Optional.of(employee);
		when(employeeRepository.findById(1001)).thenReturn(optionalOfEmployee);
		
		Employee employeeUpdated=new Employee();
		employeeUpdated.setEmpId(1001);
		employeeUpdated.setEmpSalary(45000);
		employeeUpdated.setEmpName("sabbir");
		employeeUpdated.setEmpDesignation("Trainer");
		when(employeeRepository.save(employee)).thenReturn(employeeUpdated);
		String actual=employeeServiceImpl.updateEmployeeRequestSalary(1001, 45000);
		assertEquals("success",actual);
		}catch(Exception e) {
			assertTrue(false);
		}
		
	}
	@Test
	public void getEmployeeResponseModels_positiveWhenModelIsFound() {
		try {
		Iterable<Employee> iterableMock=Mockito.mock(Iterable.class);
		when(employeeRepository.findAll()).thenReturn(iterableMock);
		Iterator<Employee> iteratorMock=Mockito.mock(Iterator.class);
		when(iterableMock.iterator()).thenReturn(iteratorMock);
		when(iteratorMock.hasNext()).thenReturn(true).thenReturn(false);
		Employee employeeMock=Mockito.mock(Employee.class);
		when(iteratorMock.next()).thenReturn(employeeMock);
		when(employeeMock.getEmpId()).thenReturn(1001);
		when(employeeMock.getEmpName()).thenReturn("Sabbir");
		when(employeeMock.getEmpSalary()).thenReturn(45000D);
		when(employeeMock.getEmpDesignation()).thenReturn("Trainer");
		
		List<EmployeeResponse> list=employeeServiceImpl.getEmployeeResponseModels();
		assertTrue(list.size()==1);
		}catch(Exception e) {
			assertTrue(false);
		}
	}

	@Test
	public void getEmployeeResponseModels_positiveWhenMoreThanOneModelIsFound() {
		try {
		Iterable<Employee> iterableMock=Mockito.mock(Iterable.class);
		when(employeeRepository.findAll()).thenReturn(iterableMock);
		Iterator<Employee> iteratorMock=Mockito.mock(Iterator.class);
		when(iterableMock.iterator()).thenReturn(iteratorMock);
		when(iteratorMock.hasNext()).thenReturn(true).thenReturn(true).thenReturn(false);
		Employee employeeMock=Mockito.mock(Employee.class);
		when(iteratorMock.next()).thenReturn(employeeMock);
		when(employeeMock.getEmpId()).thenReturn(1001);
		when(employeeMock.getEmpName()).thenReturn("Sabbir");
		when(employeeMock.getEmpSalary()).thenReturn(45000D);
		when(employeeMock.getEmpDesignation()).thenReturn("Trainer");
		
		List<EmployeeResponse> list=employeeServiceImpl.getEmployeeResponseModels();
		assertTrue(list.size()>1);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	@Test
	public void getEmployeeResponseModels_Exception() {
		try {
		Iterable<Employee> iterableMock=Mockito.mock(Iterable.class);
		when(employeeRepository.findAll()).thenReturn(iterableMock);
		Iterator<Employee> iteratorMock=Mockito.mock(Iterator.class);
		when(iterableMock.iterator()).thenReturn(iteratorMock);
		when(iteratorMock.hasNext()).thenReturn(false);
		Employee employeeMock=Mockito.mock(Employee.class);
		when(iteratorMock.next()).thenReturn(employeeMock);
		when(employeeMock.getEmpId()).thenReturn(1001);
		when(employeeMock.getEmpName()).thenReturn("Sabbir");
		when(employeeMock.getEmpSalary()).thenReturn(45000D);
		when(employeeMock.getEmpDesignation()).thenReturn("Trainer");
		
		List<EmployeeResponse> list=employeeServiceImpl.getEmployeeResponseModels();
		assertTrue(false);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void getEmployeeResponseModelById_Positive() {
		try {
		int empIdTestData=1001;
		Employee employee=new Employee();
		employee.setEmpId(1001);
		employee.setEmpName("sabbir");
		employee.setEmpSalary(45000D);
		employee.setEmpDesignation("Trainer");
		
	
		Optional<Employee> optionalOfEmployee=Optional.of(employee);
		when(employeeRepository.findById(empIdTestData)).thenReturn(optionalOfEmployee);
		EmployeeResponse employeeResponse=employeeServiceImpl.getEmployeeResponseModelById(empIdTestData);
		assertTrue(employeeResponse!=null);
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	@Test
	public void getEmployeeResponseModelById_negative() {
		try {
		int empIdTestData=1001;
		Optional<Employee> optionalOfEmployee=Optional.ofNullable(null);
		when(employeeRepository.findById(empIdTestData)).thenReturn(optionalOfEmployee);
		EmployeeResponse employeeResponse=employeeServiceImpl.getEmployeeResponseModelById(empIdTestData);
		assertTrue(employeeResponse.getEmpId()==0);
		}catch(Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	@Test
	public void persistEmployeeRequest_Positive() {
	try {
		Employee mockOfEmployee=Mockito.mock(Employee.class);
		EmployeeRequest employeeRequest=new EmployeeRequest();
		employeeRequest.setEmpId(1001);
		employeeRequest.setEmpName("sabbir");
		employeeRequest.setEmpSalary(45000D);
		employeeRequest.setEmpDesignation("Trainer");
		
		when(employeeRepository.save(Mockito.any())).thenReturn(mockOfEmployee);
		String actual=employeeServiceImpl.persistEmployeeRequest(employeeRequest);
		assertEquals("success",actual);
	}catch(Exception e) {
		e.printStackTrace();
		assertTrue(false);
	}
		
		
	}
	@Test
	public void persistEmployeeRequest_Negative() {
	try {
		Employee mockOfEmployee=Mockito.mock(Employee.class);
		EmployeeRequest employeeRequest=new EmployeeRequest();
		employeeRequest.setEmpId(0);
		employeeRequest.setEmpName("sabbir");
		employeeRequest.setEmpSalary(45000D);
		employeeRequest.setEmpDesignation("Trainer");
		
		when(employeeRepository.save(Mockito.any())).thenReturn(mockOfEmployee);
		employeeServiceImpl.persistEmployeeRequest(employeeRequest);
		assertTrue(false);
	}catch(Exception e) {
		
		assertTrue(true);
	}
	}
	@Test
	public void persistEmployeeRequest_Exception() {
	try {
		EmployeeRequest employeeRequest=new EmployeeRequest();
		employeeRequest.setEmpId(0);
		employeeRequest.setEmpName("sabbir");
		employeeRequest.setEmpSalary(45000D);
		employeeRequest.setEmpDesignation("Trainer");
		
		when(employeeRepository.save(Mockito.any())).thenThrow(SQLException.class);
		employeeServiceImpl.persistEmployeeRequest(employeeRequest);
		assertTrue(false);
	}catch(Exception e) {
		
		assertTrue(true);
	}
	
	}
	@Test
	public void deleteEmployeeRequest_Positive() {
		try {
		int empIdTest=1001;
		String actual=employeeServiceImpl.deleteEmployeeRequest(empIdTest);
		verify(employeeRepository,times(1)).deleteById(empIdTest);
		assertEquals("success",actual);
		}catch(Exception e) {
			assertTrue(false);
		}
	}
	@Test
	public void deleteEmployeeRequest_Negative() {
		try {
		int empIdTest=-1;
		employeeServiceImpl.deleteEmployeeRequest(empIdTest);
		verify(employeeRepository,times(1)).deleteById(empIdTest);
		}catch(Exception e) {
			assertTrue(true);
		}
	}
}
