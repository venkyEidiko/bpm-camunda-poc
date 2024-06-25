package com.bpm_camunda_service.pack.repository;

import com.bpm_camunda_service.pack.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByEmpId(int empId);
}
