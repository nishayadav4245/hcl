package com.pharmacy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private Long id;
    private Long medicineId;
    private String medicineName;
    private Integer quantity;
    private LocalDateTime lastUpdated;
}
