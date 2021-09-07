package com.marvelchallenge.usecase.translation.impl;

import com.marvelchallenge.gateway.translation.TranslationGateway;
import com.marvelchallenge.gateway.translation.dtos.TranslationRequestDTO;
import com.marvelchallenge.gateway.translation.dtos.TranslationResponseDTO;
import com.marvelchallenge.models.Character;
import com.marvelchallenge.usecase.translation.GetTranslations;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
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
            final Integer NAME = 0;
            final Integer DESCRIPTION = 1;
            var request = mountRequest(character);
            List<TranslationResponseDTO> response = translationGateway
                    .getTranslations(request, to, key, region);
            character.setName(response.get(NAME).getTranslations().get(0).getText());
            character.setDescription(response.get(DESCRIPTION).getTranslations().get(0).getText());
        }
    }

    private List<TranslationRequestDTO> mountRequest(Character character) {
        List<String> texts = List.of(character.getName()
                , character.getDescription());
        return texts.stream()
                .map(text -> TranslationRequestDTO.builder()
                        .text(text)
                        .build()).collect(Collectors.toList());
    }
}
