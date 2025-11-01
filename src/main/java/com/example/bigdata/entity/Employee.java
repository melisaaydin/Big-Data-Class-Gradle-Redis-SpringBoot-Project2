package com.example.bigdata.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.Date;

@Entity
@Table(name = "employee")
@JsonIgnoreProperties(value = {"manager"}, allowSetters = true)
public class Employee {
    @Id
    private Long empno;
    private String ename;
    private String job;
    private Long mgr;
    @Temporal(TemporalType.DATE)
    private Date hiredate;
    private Double sal;
    private Double comm;
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "mgr", insertable = false, updatable = false)
    private Employee manager;

    public Long getEmpno() { return empno; }
    public void setEmpno(Long empno) { this.empno = empno; }
    public String getEname() { return ename; }
    public void setEname(String ename) { this.ename = ename; }
    public String getJob() { return job; }
    public void setJob(String job) { this.job = job; }
    public Long getMgr() { return mgr; }
    public void setMgr(Long mgr) { this.mgr = mgr; }
    public Date getHiredate() { return hiredate; }
    public void setHiredate(Date hiredate) { this.hiredate = hiredate; }
    public Double getSal() { return sal; }
    public void setSal(Double sal) { this.sal = sal; }
    public Double getComm() { return comm; }
    public void setComm(Double comm) { this.comm = comm; }
    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }
    public String getImgUrl() { return imgUrl; }
    public void setImgUrl(String imgUrl) { this.imgUrl = imgUrl;}

}
