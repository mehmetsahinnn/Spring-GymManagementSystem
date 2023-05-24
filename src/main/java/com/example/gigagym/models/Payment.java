package com.example.gigagym.models;


import jakarta.persistence.Entity;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "SID")
    private int SID;

    @Column(name = "salary")
    private double salary;

    @Column(name = "paymentStatus")
    private int paymentStatus;

    @Column(name = "paymentDate")
    private Date paymentDate;



    public Payment() {
    }

    public Payment(int SID, double salary, int paymentStatus, Date paymentDate) {
        this.SID = SID;
        this.salary = salary;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

}
