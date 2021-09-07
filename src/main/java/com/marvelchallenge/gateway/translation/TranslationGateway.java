package com.marvelchallenge.gateway.translation;

import com.marvelchallenge.gateway.translation.dtos.TranslationRequestDTO;
import com.marvelchallenge.gateway.translation.dtos.TranslationResponseDTO;

import java.util.List;

public interface TranslationGateway {

    List<TranslationResponseDTO> getTranslations(
            List<TranslationRequestDTO> texts
            , String to
            , String Key
            , String region);
}
