package com.example.gigagym.repositories;

import com.example.gigagym.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer>, JpaSpecificationExecutor<Staff> {

    Staff findByEmailAddress(String emailAddress);

    Staff findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Staff s SET s.name = :name, s.password = :password, s.emailAddress = :emailAddress, s.jobTitle = :jobTitle, s.daysOfWork = :daysOfWork, s.startDate = :startDate WHERE s.id = :id")
    void updateStaff(@Param("id") Integer id,
                     @Param("name") String name,
                     @Param("emailAddress") String emailAddress,
                     @Param("password") String password,
                     @Param("jobTitle") String jobTitle,
                     @Param("daysOfWork") Integer daysOfWork,
                     @Param("startDate") Date startDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM Staff s WHERE s.id = :id")
    void deleteStaff(@Param("id") Integer id);
}

