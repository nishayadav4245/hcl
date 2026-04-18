package com.pharmacy.repository;

import com.pharmacy.entity.Prescription;
import com.pharmacy.enums.PrescriptionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    Page<Prescription> findByUserId(Long userId, Pageable pageable);

    Page<Prescription> findByStatus(PrescriptionStatus status, Pageable pageable);
}
