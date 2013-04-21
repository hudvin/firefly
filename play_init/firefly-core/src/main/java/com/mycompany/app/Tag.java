package com.mycompany.app;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created with IntelliJ IDEA.
 * User: hudvin
 * Date: 4/17/13
 * Time: 4:09 PM
 * To change this template use File | Settings | File Templates.
 */


@Entity
@Table (name = "Tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private String value;

    public Tag() {
    }

    public Tag(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
