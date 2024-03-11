package com.cognizant.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.entities.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Integer> {

	
}
