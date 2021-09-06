package com.marvelchallenge.gateway.characters;

import com.marvelchallenge.gateway.characters.dto.CharacterDto;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "CharactersClient", url = "${marvel-api.base-url}")
public interface CharactersClient {

    @GetMapping(path = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    MarvelApiResponse<CharacterDto> getAll(
            @RequestParam(value = "ts") String ts,
            @RequestParam(value = "apikey") String publicKey,
            @RequestParam(value = "hash") String hash
    );


}
