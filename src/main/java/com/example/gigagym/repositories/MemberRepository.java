package com.example.gigagym.repositories;

import com.example.gigagym.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT m FROM Member m WHERE m.id IS NOT NULL AND m.id > 0")
    Page<Member> findMembers(Pageable pageable);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.sex = :sex")
    long countBySex(@Param("sex") String sex);

    @Query("SELECT SUM(charge) FROM Member")
    double sumCharges();

    @Query("SELECT sum(charge * (MONTH(CURDATE()) - MONTH(startDate))) AS totalCharge FROM Member")
    double calculateTotalCharges();

    @Query("SELECT COUNT(m) FROM Member m WHERE m.startDate BETWEEN :startDate AND :endDate")
    int countByStartDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Modifying
    @Transactional
    @Query("UPDATE Member m SET m.name = :name, m.emailAddress = :emailAddress, m.sex = :sex, m.address = :address, m.charge = :charge, m.ptId = :ptId, m.startDate = :startDate, m.endDate = :endDate WHERE m.id = :id")
    void updateMember(@Param("id") Integer id,
                      @Param("name") String name,
                      @Param("emailAddress") String emailAddress,
                      @Param("sex") String sex,
                      @Param("address") String address,
                      @Param("charge") double charge,
                      @Param("ptId") int ptId,
                      @Param("startDate") Date startDate,
                      @Param("endDate") Date endDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM Member m WHERE m.id = :id")
    void deleteMember(@Param("id") Integer id);

}
