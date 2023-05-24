package com.example.gigagym.repositories;


import com.example.gigagym.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p.SID, SUM(p.salary) FROM Payment p GROUP BY p.SID")
    List<Object[]> calculateTotalPaymentsBySID();
}