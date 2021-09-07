package com.marvelchallenge.usecase.translation.impl;

import com.marvelchallenge.exception.BusinessRuleException;
import com.marvelchallenge.gateway.translation.TranslationGateway;
import com.marvelchallenge.gateway.translation.dto.TranslationDataResponseDTO;
import com.marvelchallenge.gateway.translation.dto.TranslationRequestDTO;
import com.marvelchallenge.gateway.translation.dto.TranslationResponseDTO;
import com.marvelchallenge.models.Character;
import com.marvelchallenge.usecase.translation.GetTranslations;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetTranslationsImpl implements GetTranslations {

    @Value("${translation-api-auth.key}")
    private String key;
    @Value("${translation-api-auth.region}")
    private String region;

    private final TranslationGateway translationGateway;

    @Override
    public void execute(Character character, String to) {
        if (!"en".equals(to) && ObjectUtils.allNotNull(key, region)) {

            var request = mountRequest(character);
            try {
                List<TranslationResponseDTO> translated = translationGateway
                        .getTranslations(request, to, key, region);
                character.setDescription(getText(translated, character.getDescription()));
            } catch (FeignException exception) {
                throw new BusinessRuleException(String
                        .format("Error when trying to translate by code language ´%s´", to));
            }
        }
    }
    private String getText(List<TranslationResponseDTO> response, String initialText){
        response = Optional.ofNullable(response).orElse(new ArrayList<>());

        return response
                .stream()
                .map(item -> {
                    TranslationDataResponseDTO translated = item.getTranslations()
                            .stream()
                            .findFirst()
                            .orElse(TranslationDataResponseDTO.builder().text(initialText).build());
                    return translated.getText();
                }).findFirst().orElse(initialText);
    }

    private List<TranslationRequestDTO> mountRequest(Character character) {
        List<String> texts = List.of(character.getDescription());
        return texts.stream()
                .map(text -> TranslationRequestDTO.builder()
                        .text(text)
                        .build()).collect(Collectors.toList());
    }
}
