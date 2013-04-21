package com.mycompany.app;

/**
 * Created with IntelliJ IDEA.
 * User: hudvin
 * Date: 4/17/13
 * Time: 3:52 PM
 * To change this template use File | Settings | File Templates.
 */


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Account")

public class Account {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "account_tag", joinColumns = {@JoinColumn(name = "account_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private Set<Tag> tags = new HashSet<Tag>();


    public Account() {
    }


    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
