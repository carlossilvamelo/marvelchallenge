package com.marvelchallenge.gateway.translation.impl;

import com.marvelchallenge.gateway.translation.TranslationGateway;
import com.marvelchallenge.gateway.translation.client.TranslationClient;
import com.marvelchallenge.gateway.translation.dto.TranslationRequestDTO;
import com.marvelchallenge.gateway.translation.dto.TranslationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TranslationGatewayImpl implements TranslationGateway {


    private final TranslationClient translationClient;

    @Override
    public List<TranslationResponseDTO> getTranslations(List<TranslationRequestDTO> texts
            , String to, String key
            , String region) {
        return translationClient.getTranslations(texts, to, key, region);
    }
}
