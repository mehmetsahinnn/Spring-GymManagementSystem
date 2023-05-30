package com.example.gigagym.repositories;

import com.example.gigagym.models.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {


    @Query("SELECT e.type, COUNT(e.type) FROM Equipment e GROUP BY e.type ORDER BY COUNT(e.type) DESC")
    List<Object[]> countEquipmentByType();

    @Query("SELECT e FROM Equipment e WHERE e.type = :type")
    List<Equipment> findByType(@Param("type") String type);

}

