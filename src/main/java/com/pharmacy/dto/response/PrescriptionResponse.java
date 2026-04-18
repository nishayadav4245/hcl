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
public class PrescriptionResponse {

    private Long id;
    private String username;
    private String imageUrl;
    private String status;
    private String notes;
    private LocalDateTime uploadedAt;
    private LocalDateTime verifiedAt;
}
