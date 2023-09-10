package com.g3.springandangular_group3_ams.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    private String fileName;
    private String fileUrl;
    private String fileType;
    private Long size;
}
