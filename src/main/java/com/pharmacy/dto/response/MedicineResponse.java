package com.pharmacy.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineResponse {

    private Long id;
    private String name;
    private String description;
    private String manufacturer;
    private String dosage;
    private String packaging;
    private BigDecimal price;
    private Boolean prescriptionRequired;
    private String imageUrl;
    private String categoryName;
    private Long categoryId;
    private Integer stockQuantity;
}
