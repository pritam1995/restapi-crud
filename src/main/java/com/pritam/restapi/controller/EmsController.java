package com.pritam.restapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pritam.restapi.entity.Employee;
import com.pritam.restapi.repository.EmployeeRepository;

@RestController
public class EmsController implements ErrorController {

	@Autowired
	EmployeeRepository employeeRepository;

	@GetMapping(path = "/", produces = "application/json")
	public Map<String, String> landingPage() {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Success", "Welcome to EMS API!");
		return hashMap;

	}

	@GetMapping(path = "/emp", produces = "application/json")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping(path = "/emp/{id}", produces = "application/json")
	public Employee getEmployee(@PathVariable("id") Integer id) {
		return employeeRepository.findById(id).get();
	}

	@PostMapping(path = "/emp", produces = "application/json")
	public Employee createProduct(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@DeleteMapping(path = "/emp/{id}", produces = "application/json")
	public HashMap<Integer, String> deleteProduct(@PathVariable("id") int id) {
		employeeRepository.deleteById(id);
		HashMap<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(id, "is successfuly deleted..");
		return hashMap;
	}

	@RequestMapping("/error")
	public HashMap<String, String> handleError() {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("Sorry", "Something went wrong!");
		return hashMap;
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}
