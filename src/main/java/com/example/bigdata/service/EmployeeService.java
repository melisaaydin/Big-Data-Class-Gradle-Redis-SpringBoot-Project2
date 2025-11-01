package com.example.bigdata.service;

import com.example.bigdata.entity.Employee;
import com.example.bigdata.repository.EmployeeRepository;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private FileSystem fileSystem;

    @Autowired
    private RedisTemplate<String, Employee> redisEmployeeTemplate;

    public Employee getEmployeeByEmpno(Long empno) {
        String key = "employee_obj:" + empno;
        Employee emp = redisEmployeeTemplate.opsForValue().get(key);

        if (emp == null) {
            emp = employeeRepository.findById(empno).orElse(null);

            if (emp != null) {
                redisEmployeeTemplate.opsForValue().set(key, emp, 1, TimeUnit.HOURS);
            }
        }
        return emp;
    }

    public List<Employee> findAllSimple() {
        return employeeRepository.findAll();
    }
    public List<Employee> findAllWithDepartmentsAndManager() {
        return employeeRepository.findAllWithDepartmentsAndManager();
    }
    @Scheduled(fixedRate = 60000)
    public void flushToRedis() {
        List<Employee> employees = employeeRepository.findAll();
        for (Employee emp : employees) {
            redisTemplate.opsForValue().set("employee:" + emp.getEmpno(), emp.getEname() + "," + emp.getJob());
        }
    }

    public void uploadImagesToHDFS() throws IOException {
        File localDir = new File("/home/melisa/images");
        if (localDir.exists() && localDir.isDirectory()) {
            File[] files = localDir.listFiles();
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (file.isFile() &&
                            !fileName.contains(":Zone.Identifier") &&
                            (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".png"))) {

                        Path hdfsPath = new Path("/images/" + fileName);

                        try (FSDataOutputStream out = fileSystem.create(hdfsPath, true);
                             java.io.FileInputStream in = new java.io.FileInputStream(file)) {

                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = in.read(buffer)) > 0) {
                                out.write(buffer, 0, bytesRead);
                            }
                            System.out.println("HDFS'e yüklendi: " + fileName);
                        }
                    }
                }
            }
        }
    }
}
