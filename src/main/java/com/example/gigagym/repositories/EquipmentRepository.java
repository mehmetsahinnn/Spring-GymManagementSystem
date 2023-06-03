package com.example.gigagym.repositories;

import com.example.gigagym.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Equipment e WHERE e.id = :id")
    void deleteEquipment(@Param("id") Long id);


    @Query("SELECT e.type, COUNT(e.type) FROM Equipment e GROUP BY e.type ORDER BY COUNT(e.type) DESC")
    List<Object[]> countEquipmentByType();


}

