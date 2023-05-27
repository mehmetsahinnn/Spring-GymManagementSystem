package com.example.gigagym.repositories;


import com.example.gigagym.models.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Page<Payment> findAll(Pageable pageable);

    @Query("SELECT p.SID, SUM(p.salary) FROM Payment p GROUP BY p.SID")
    List<Object[]> calculateTotalPaymentsBySID();
}