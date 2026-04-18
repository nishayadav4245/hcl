package com.pharmacy.repository;

import com.pharmacy.entity.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    Page<Medicine> findByCategoryId(Long categoryId, Pageable pageable);

    List<Medicine> findByNameContainingIgnoreCase(String name);

    Page<Medicine> findByPrescriptionRequired(Boolean prescriptionRequired, Pageable pageable);
}
