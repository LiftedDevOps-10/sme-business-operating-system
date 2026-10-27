package com.lifted.smebos.repository;

import com.lifted.smebos.entity.AuditEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditEventRepository extends JpaRepository<AuditEvent, Long> {

    List<AuditEvent> findByUserId(Long userId);

    List<AuditEvent> findByAction(String action);

    List<AuditEvent> findAllByOrderByTimestampDesc();
}