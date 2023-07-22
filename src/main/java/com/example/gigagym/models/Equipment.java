package com.example.gigagym.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "model")
    private String model;

    @Column(name = "purchaseDate")
    private Date purchaseDate;

    public Equipment(String type, String manufacturer, String model, Date purchaseDate) {
        this.type = type;
        this.manufacturer = manufacturer;
        this.model = model;
        this.purchaseDate = purchaseDate;
    }
}
