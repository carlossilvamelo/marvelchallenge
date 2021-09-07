package com.marvelchallenge.gateway.translation;

import com.marvelchallenge.gateway.translation.dto.TranslationRequestDTO;
import com.marvelchallenge.gateway.translation.dto.TranslationResponseDTO;

import java.util.List;

public interface TranslationGateway {

    List<TranslationResponseDTO> getTranslations(
            List<TranslationRequestDTO> texts
            , String to
            , String Key
            , String region);
}
