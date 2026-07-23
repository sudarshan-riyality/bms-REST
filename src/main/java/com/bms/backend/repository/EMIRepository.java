package com.bms.backend.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bms.backend.entity.EMI;

@Repository
public interface EMIRepository extends JpaRepository<EMI, UUID> {

}