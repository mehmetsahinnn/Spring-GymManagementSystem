package com.example.gigagym.repositories;

import com.example.gigagym.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceRepository extends JpaRepository<Equipment, Long> {

    @Query("SELECT COUNT(e) FROM Equipment e WHERE e.id IN (SELECT m.equipmentId FROM Maintenance m WHERE m.dateOfNextMaintenance > ?1)")
    int countEquipmentWithUpcomingMaintenance(java.util.Date currentDate);
}
