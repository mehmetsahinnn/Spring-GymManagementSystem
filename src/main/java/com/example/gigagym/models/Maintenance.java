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
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "EQID")
    private int equipmentId;

    @Column(name = "MID")
    private int maintenanceId;

    @Column(name = "dateOfMaintenance")
    private Date dateOfMaintenance;

    @Column(name = "dateOfNextMaintenance")
    private Date dateOfNextMaintenance;


    public Maintenance(int equipmentId, int maintenanceId, Date dateOfMaintenance, Date dateOfNextMaintenance) {
        this.equipmentId = equipmentId;
        this.maintenanceId = maintenanceId;
        this.dateOfMaintenance = dateOfMaintenance;
        this.dateOfNextMaintenance = dateOfNextMaintenance;
    }

}
