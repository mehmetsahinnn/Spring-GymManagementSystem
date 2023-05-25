package com.example.gigagym.repositories;

import com.example.gigagym.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.Date;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    Page<Member> findAll(Pageable pageable);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.startDate BETWEEN :startDate AND :endDate")
    int countByRegistrationDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
