package com.example.gigagym.repositories;

import com.example.gigagym.models.Maintenance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {


    @Query(value = "SELECT * FROM maintenance WHERE dateOfNextMaintenance >= NOW() AND dateOfNextMaintenance <= DATE_ADD(NOW(), INTERVAL 7 DAY)\n", nativeQuery = true)
    List<Maintenance> findByDateOfNextMaintenanceBetween(Date startDate, Date endDate);

    @Query(value = "SELECT * FROM maintenance WHERE dateOfNextMaintenance >= :startDate AND dateOfNextMaintenance <= :endDate", nativeQuery = true)
    Page<Maintenance> findByDateOfNextMaintenanceBetween(Date startDate, Date endDate, Pageable pageable);



}
