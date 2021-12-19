package com.example.l2_1.repository;

import com.example.l2_1.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LogRepository  extends JpaRepository<Log, UUID> {
}

