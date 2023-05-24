package com.example.gigagym.repositories;

import com.example.gigagym.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT COUNT(m) FROM Member m WHERE m.startDate BETWEEN :startDate AND :endDate")
    int countByRegistrationDateBetween(LocalDate startDate, LocalDate endDate);

}
