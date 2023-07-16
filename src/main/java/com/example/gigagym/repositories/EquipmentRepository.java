package com.example.gigagym.repositories;

import com.example.gigagym.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long>, JpaSpecificationExecutor<Equipment> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Equipment e WHERE e.id = :id")
    void deleteEquipment(@Param("id") Long id);


    @Query("SELECT e.type, COUNT(e.type) FROM Equipment e GROUP BY e.type ORDER BY COUNT(e.type) DESC")
    List<Object[]> countEquipmentByType();

    @Modifying
    @Transactional
    @Query("UPDATE Equipment e SET e.type = :type, e.manufacturer = :manufacturer, e.model = :model, e.purchaseDate = :purchaseDate WHERE e.id = :id")
    void updateEquipment(
            @Param("id") Integer id,
            @Param("type") String type,
            @Param("manufacturer") String manufacturer,
            @Param("model") String model,
            @Param("purchaseDate") Date purchaseDate);

}

