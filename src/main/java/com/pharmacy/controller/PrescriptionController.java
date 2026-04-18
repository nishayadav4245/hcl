package com.pharmacy.controller;

import com.pharmacy.dto.response.ApiResponse;
import com.pharmacy.dto.response.PrescriptionResponse;
import com.pharmacy.enums.PrescriptionStatus;
import com.pharmacy.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
@RequiredArgsConstructor
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<ApiResponse<PrescriptionResponse>> uploadPrescription(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String imageUrl,
            @RequestParam(required = false) String notes) {
        PrescriptionResponse response = prescriptionService.uploadPrescription(
                userDetails.getUsername(), imageUrl, notes);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Prescription uploaded", response));
    }

    @GetMapping("/my")
    public ResponseEntity<ApiResponse<Page<PrescriptionResponse>>> getMyPrescriptions(
            @AuthenticationPrincipal UserDetails userDetails,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(
                prescriptionService.getUserPrescriptions(userDetails.getUsername(), pageable)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> getPrescriptionById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(prescriptionService.getPrescriptionById(id)));
    }

    @GetMapping("/pending")
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIST')")
    public ResponseEntity<ApiResponse<Page<PrescriptionResponse>>> getPendingPrescriptions(
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(prescriptionService.getPendingPrescriptions(pageable)));
    }

    @PutMapping("/{id}/verify")
    @PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIST')")
    public ResponseEntity<ApiResponse<PrescriptionResponse>> verifyPrescription(
            @PathVariable Long id,
            @RequestParam PrescriptionStatus status,
            @RequestParam(required = false) String notes) {
        return ResponseEntity.ok(ApiResponse.success("Prescription verified",
                prescriptionService.verifyPrescription(id, status, notes)));
    }
}
