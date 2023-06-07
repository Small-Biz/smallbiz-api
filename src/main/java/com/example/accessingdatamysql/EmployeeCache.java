package com.example.accessingdatamysql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeCache {

	private Map<String,List<Employee>> countMap=new HashMap<String,List<Employee>>();	
	
	public void start() {
		
	}
	
	//get the next employee
	public void push(List<Employee> employeeList) {
		
		/*
		for ( Employee employee:employeeList) {
			if (employee.getDepartment() !=null) {
				String department=employee.getDepartment();
				if (countMap.containsKey(department)) {
					countMap.get(department).add(employee);
				}else {
					countMap.put(department, new ArrayList<Employee>());
					countMap.get(department).add(employee);
				}
			}
		}*/
		
		employeeList.stream().forEach(e->{
			if (e.getDepartment() !=null) {
				String department=e.getDepartment();
				if (countMap.containsKey(department)) {
					countMap.get(department).add(e);
				}else {
					countMap.put(department, new ArrayList<Employee>());
					countMap.get(department).add(e);
				}
			}
					});
	}
	
	public void finish(){
		
	}
	
	//return amount of employeeList
	public int fetchCount(String department) {
		
		if (countMap.containsKey(department)) {
			return countMap.get(department).size();
		}else {
			return 0;
		}		

	}
	
}
