package com.example.gigagym.repositories;


import com.example.gigagym.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {


    @Query("SELECT p FROM Payment p WHERE p.id IS NOT NULL AND p.id > 0")
    Page<Payment> findAll(Pageable pageable);

    @Query("SELECT p FROM Payment p WHERE p.id = :id")
    Optional<Payment> findPaymentById(@Param("id") Long id);


    @Query("SELECT p.SID, SUM(p.salary) FROM Payment p WHERE p.paymentStatus = 1 AND p.paymentDate BETWEEN :startDate AND :currentDate GROUP BY p.SID")
    List<Object[]> calculateTotalPaymentsBySID(@Param("startDate") Date startDate, @Param("currentDate") Date currentDate);


    @Transactional
    @Modifying
    @Query("UPDATE Payment p SET p.paymentStatus = 1 WHERE p.id = :paymentId")
    void setPaymentStatusToOne(Integer paymentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Payment p WHERE p.id = :id")
    void deletePayment(@Param("id") Integer id);
}