package com.example.gigagym.models;


import jakarta.persistence.*;
import java.util.Date;

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


    public Maintenance() {
    }

    public Maintenance(int equipmentId, int maintenanceId, Date dateOfMaintenance, Date dateOfNextMaintenance) {
        this.equipmentId = equipmentId;
        this.maintenanceId = maintenanceId;
        this.dateOfMaintenance = dateOfMaintenance;
        this.dateOfNextMaintenance = dateOfNextMaintenance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getMaintenanceId() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId = maintenanceId;
    }

    public Date getDateOfMaintenance() {
        return dateOfMaintenance;
    }

    public void setDateOfMaintenance(Date dateOfMaintenance) {
        this.dateOfMaintenance = dateOfMaintenance;
    }

    public Date getDateOfNextMaintenance() {
        return dateOfNextMaintenance;
    }

    public void setDateOfNextMaintenance(Date dateOfNextMaintenance) {
        this.dateOfNextMaintenance = dateOfNextMaintenance;
    }
}
