package com.marvelchallenge.gateway.translation.client;

import com.marvelchallenge.gateway.translation.dto.TranslationRequestDTO;
import com.marvelchallenge.gateway.translation.dto.TranslationResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "TranslationClient", url = "${translation-api.base-url}")
public interface TranslationClient {

    @PostMapping("/translate?from=en&api-version=3.0")
    List<TranslationResponseDTO> getTranslations(
            @RequestBody List<TranslationRequestDTO> texts
            , @RequestParam("to") String to
            , @RequestHeader("Ocp-Apim-Subscription-Key") String subKey
            , @RequestHeader("Ocp-Apim-Subscription-Region") String region);
}
