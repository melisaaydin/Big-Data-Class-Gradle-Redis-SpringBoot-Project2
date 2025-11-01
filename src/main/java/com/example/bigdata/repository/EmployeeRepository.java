package com.example.bigdata.repository;

import com.example.bigdata.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e, m.ename as managerName FROM Employee e JOIN FETCH e.department d LEFT JOIN Employee m ON e.mgr = m.empno")
    List<Object[]> findAllEmployeesWithManagerName();

    @Query("SELECT e FROM Employee e JOIN FETCH e.department d LEFT JOIN e.manager m")
    List<Employee> findAllWithDepartmentsAndManager();

}