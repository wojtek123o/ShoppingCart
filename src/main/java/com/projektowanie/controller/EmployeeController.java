package com.projektowanie.controller;

import com.projektowanie.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/mainScreen")
    public String getEmployeeMainScreen(Model model) {
        return "employeeMainScreen";
    }

    @GetMapping("/manageProducts")
    public String getEmployeeManageProductsScreen(Model model) {
        return "employeeManageProductsScreen";
    }


    @GetMapping("/manageComplaints")
    public String getEmployeeManageComplaintsScreen(Model model) {
        return "employeeManageComplaintsScreen";
    }


}
