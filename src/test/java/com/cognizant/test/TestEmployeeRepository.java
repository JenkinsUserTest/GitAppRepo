package com.cognizant.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cognizant.entities.Employee;
import com.cognizant.main.EmployeeDbSpringDatajpaApplication;
import com.cognizant.repositories.EmployeeRepository;
@DataJpaTest
@ContextConfiguration(classes = EmployeeDbSpringDatajpaApplication.class)
class TestEmployeeRepository {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TestEntityManager entityManager;
	@Test
	public void testFindAllPositive() {
		Employee e=new Employee();
		e.setEmpId(1001);
		e.setEmpName("test");
		e.setEmpSalary(34000);
		e.setEmpDesignation("test");
		entityManager.persist(e);
		Iterable<Employee> it=employeeRepository.findAll();
		assertTrue(it.iterator().hasNext());
	}
	@Test
	public void testFindAllNegative() {
		Iterable<Employee> it=employeeRepository.findAll();
		assertTrue(!it.iterator().hasNext());
	}
	
	
	@Test
	public void testFindByIdPositive() {
		Employee e=new Employee();
		e.setEmpId(1001);
		e.setEmpName("test");
		e.setEmpSalary(34000);
		e.setEmpDesignation("test");
		entityManager.persist(e);
		Optional<Employee> employee=employeeRepository.findById(1001);
		assertTrue(employee.isPresent());
	}
	
	@Test
	public void testFindByIdNegative() {
		Optional<Employee> employee=employeeRepository.findById(1001);
		assertTrue(!employee.isPresent());
	}
	
	@Test
	public void testSavePositive() {
		Employee e=new Employee();
		e.setEmpId(1001);
		e.setEmpName("test");
		e.setEmpSalary(34000);
		e.setEmpDesignation("test");
		employeeRepository.save(e);
		Optional<Employee> employee=employeeRepository.findById(1001);
		assertTrue(employee.isPresent());
	}
	
	@Test
	public void testDeletePositive() {
		Employee e=new Employee();
		e.setEmpId(1001);
		e.setEmpName("test");
		e.setEmpSalary(34000);
		e.setEmpDesignation("test");
		entityManager.persist(e);
		employeeRepository.delete(e);
		Optional<Employee> employee=employeeRepository.findById(1001);
		assertTrue(!employee.isPresent());
	}

}
