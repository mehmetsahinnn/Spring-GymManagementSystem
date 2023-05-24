package com.example.gigagym.models;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

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

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "daysOfWork")
    private int daysOfWork;

    @Column(name = "startDate")
    private Date startDate;



    public Staff() {
    }

    public Staff(String name, String emailAddress, String jobTitle, int daysOfWork, Date startDate) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.jobTitle = jobTitle;
        this.daysOfWork = daysOfWork;
        this.startDate = startDate;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getDaysOfWork() {
        return daysOfWork;
    }

    public void setDaysOfWork(int daysOfWork) {
        this.daysOfWork = daysOfWork;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

}
