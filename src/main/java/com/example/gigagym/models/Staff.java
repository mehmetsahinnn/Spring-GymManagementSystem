package com.example.gigagym.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "password")
    private String password;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "daysOfWork")
    private Integer daysOfWork;

    @Column(name = "startDate")
    private Date startDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "staff_roles",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Staff(String name, String emailAddress,String password, String jobTitle, Integer daysOfWork, Date startDate) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.jobTitle = jobTitle;
        this.daysOfWork = daysOfWork;
        this.startDate = startDate;
    }

    public void addRole(Role role) {
        this.roles.add(role);
        role.getStaffs().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getStaffs().remove(this);
    }
}
