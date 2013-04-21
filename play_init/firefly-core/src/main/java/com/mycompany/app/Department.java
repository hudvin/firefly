package com.mycompany.app;

/**
 * Created with IntelliJ IDEA.
 * User: hudvin
 * Date: 4/17/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table
public class Department {

    @Id
    @GeneratedValue
    private Long id;


    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<Employee>();

    public Department() {
        super();
    }

    public Department(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
