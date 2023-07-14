package com.example.gigagym.repositories;

import com.example.gigagym.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Staff, Integer> {

    Staff findByEmailAddress(String emailAddress);

}
