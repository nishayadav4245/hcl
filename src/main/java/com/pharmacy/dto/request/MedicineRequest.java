package com.pharmacy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineRequest {

    @NotBlank(message = "Medicine name is required")
    private String name;

    private String description;

    private String manufacturer;

    private String dosage;

    private String packaging;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    private Boolean prescriptionRequired;

    private String imageUrl;

    private Long categoryId;

    private Integer stockQuantity;
}
