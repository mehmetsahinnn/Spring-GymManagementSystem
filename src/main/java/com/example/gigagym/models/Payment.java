package com.example.gigagym.models;


import jakarta.persistence.Entity;
import java.util.Date;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    public Payment(int SID, double salary, int paymentStatus, Date paymentDate) {
        this.SID = SID;
        this.salary = salary;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
    }
}
