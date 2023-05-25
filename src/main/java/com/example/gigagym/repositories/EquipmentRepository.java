package com.example.gigagym.repositories;

import com.example.gigagym.models.Equipment;
import com.example.gigagym.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    Page<Equipment> findAll(Pageable pageable);

    @Query("SELECT e.type, COUNT(e.type) FROM Equipment e GROUP BY e.type ORDER BY COUNT(e.type) DESC")
    List<Object[]> countEquipmentByType();

}

