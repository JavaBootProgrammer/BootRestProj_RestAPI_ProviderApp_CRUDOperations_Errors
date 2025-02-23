package com.nt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.model.Employee;
import com.nt.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImp implements IEmployeeService {

    @Autowired
    private IEmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> listEmployees() {

        List<Employee> list = employeeRepository.findAll();
        System.out.println("EmployeeServiceImp.listEmployees()" + list);

        System.out.print("------------");

        for (Employee country : list) {
            System.out.println("Salary " + country.getSalary());
            System.out.println("Dept  " + country.getDept());
            System.out.println("Employee Name  " + country.getEmployeeName());
            System.out.println("Job  " + country.getJob());
        }

        System.out.print("------------");
        return list;
    }

    @Override
    public Employee findByID(int id) {

        Employee employeeRecord = employeeRepository.findById(id).get();

        return employeeRecord;
    }

}