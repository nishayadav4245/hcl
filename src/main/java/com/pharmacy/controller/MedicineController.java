package com.pharmacy.controller;

import com.pharmacy.dto.request.MedicineRequest;
import com.pharmacy.dto.response.ApiResponse;
import com.pharmacy.dto.response.MedicineResponse;
import com.pharmacy.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<MedicineResponse>>> getAllMedicines(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(medicineService.getAllMedicines(pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<MedicineResponse>> getMedicineById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(medicineService.getMedicineById(id)));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ApiResponse<Page<MedicineResponse>>> getMedicinesByCategory(
            @PathVariable Long categoryId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(medicineService.getMedicinesByCategory(categoryId, pageable)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<MedicineResponse>>> searchMedicines(@RequestParam String keyword) {
        return ResponseEntity.ok(ApiResponse.success(medicineService.searchMedicines(keyword)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<MedicineResponse>> createMedicine(@Valid @RequestBody MedicineRequest request) {
        MedicineResponse response = medicineService.createMedicine(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success("Medicine created", response));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<MedicineResponse>> updateMedicine(@PathVariable Long id,
                                                                         @Valid @RequestBody MedicineRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Medicine updated", medicineService.updateMedicine(id, request)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return ResponseEntity.ok(ApiResponse.success("Medicine deleted", null));
    }
}
