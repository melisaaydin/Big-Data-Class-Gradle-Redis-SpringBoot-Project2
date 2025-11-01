package com.example.bigdata.controller;

import com.example.bigdata.entity.Employee;
import com.example.bigdata.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/all")
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeService.findAllWithDepartmentsAndManager();
        for (Employee emp : employees) {
            if (emp.getImgUrl() != null) {
                String hdfsUrl = "http://127.0.0.1:9870/webhdfs/v1/images/" + emp.getImgUrl() + "?op=OPEN";
                emp.setImgUrl(hdfsUrl);
            }
        }
        return employees;
    }


    @GetMapping("/upload")
    public String uploadImages() throws IOException {
        employeeService.uploadImagesToHDFS();
        return "Images uploaded to HDFS";
    }
}
