package com.nt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import java.util.Map;

import org.apache.catalina.mbeans.MBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nt.model.Employee;
import com.nt.service.EmployeeServiceImp;

@Controller
public class HomeController {

	@Autowired
	EmployeeServiceImp employeeServiceImp;

	@GetMapping("/home")
	public String getHome() {
		return "home";
	}

	@GetMapping("/report")
	public String report(Map<String, Object> map) {

		List<Employee> list = (List<Employee>) employeeServiceImp.listEmployees();

		for (Employee employee : list) {
			System.out.println("-----------------");
			System.out.println(employee.getDept());
			System.out.println(employee.getEmployeeName());
			System.out.println(employee.getJob());
			System.out.println(employee.getSalary());
			System.out.println("-----------------");

		}
		map.put("employees", list);
		System.out.print("list from contoller class " + employeeServiceImp.listEmployees());

		return "showReport";
	}

	@GetMapping("/edit") // display register form
	public String editEmployee(@ModelAttribute("employee") Employee receivedEmployee,
							   @RequestParam Integer employeeNo) {

		System.out.println("HomeController.editEmployee()");
		Employee foundEmployee = employeeServiceImp.findByID(employeeNo);

		BeanUtils.copyProperties(foundEmployee, receivedEmployee);

		return "editEmployee";

	}
}