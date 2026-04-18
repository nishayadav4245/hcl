package com.pharmacy.controller;

import com.pharmacy.dto.response.ApiResponse;
import com.pharmacy.dto.response.InventoryResponse;
import com.pharmacy.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN', 'PHARMACIST')")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InventoryResponse>>> getAllInventory() {
        return ResponseEntity.ok(ApiResponse.success(inventoryService.getAllInventory()));
    }

    @GetMapping("/medicine/{medicineId}")
    public ResponseEntity<ApiResponse<InventoryResponse>> getInventoryByMedicineId(@PathVariable Long medicineId) {
        return ResponseEntity.ok(ApiResponse.success(inventoryService.getInventoryByMedicineId(medicineId)));
    }

    @PutMapping("/medicine/{medicineId}")
    public ResponseEntity<ApiResponse<InventoryResponse>> updateStock(@PathVariable Long medicineId,
                                                                      @RequestParam Integer quantity) {
        return ResponseEntity.ok(ApiResponse.success("Stock updated",
                inventoryService.updateStock(medicineId, quantity)));
    }
}
