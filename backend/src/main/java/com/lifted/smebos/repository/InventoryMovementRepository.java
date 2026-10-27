package com.lifted.smebos.repository;

import com.lifted.smebos.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryMovementRepository
        extends JpaRepository<InventoryMovement, Long> {

    List<InventoryMovement> findByProductId(Long productId);

    List<InventoryMovement> findByProductIdOrderByCreatedAtDesc(Long productId);
}