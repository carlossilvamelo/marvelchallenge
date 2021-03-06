package com.marvelchallenge.gateway.translation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TranslationDataResponseDTO {
    private String text;
    private String to;
}
