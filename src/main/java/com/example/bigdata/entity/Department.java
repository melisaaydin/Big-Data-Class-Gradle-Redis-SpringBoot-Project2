package com.example.bigdata.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
    @Id
    private Long deptno;
    private String dname;
    private String loc;

    // Getter ve Setter metodları
    public Long getDeptno() { return deptno; }
    public void setDeptno(Long deptno) { this.deptno = deptno; }
    public String getDname() { return dname; }
    public void setDname(String dname) { this.dname = dname; }
    public String getLoc() { return loc; }
    public void setLoc(String loc) { this.loc = loc; }
}
