package com.example.gigagym.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Column(name = "charge")
    private double charge;

    @Column(name = "PTID")
    private int ptId;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    public Member(String name, String sex, String address, String emailAddress, double charge, int ptId, Date startDate, Date endDate) {
        this.name = name;
        this.sex = sex;
        this.address=address;
        this.emailAddress = emailAddress;
        this.charge = charge;
        this.ptId = ptId;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
