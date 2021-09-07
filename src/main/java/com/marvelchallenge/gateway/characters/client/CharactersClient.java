package com.marvelchallenge.gateway.characters.client;


import com.marvelchallenge.gateway.characters.dto.MarvelApiResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.marvelchallenge.models.Character;

@FeignClient(name = "CharactersClient", url = "${marvel-api.base-url}")
public interface CharactersClient {

    @GetMapping(path = "/characters", produces = MediaType.APPLICATION_JSON_VALUE)
    MarvelApiResponseDTO<Character> getAll(
            @RequestParam(value = "ts") String ts,
            @RequestParam(value = "apikey") String publicKey,
            @RequestParam(value = "hash") String hash
    );
    @GetMapping(path = "/characters/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    MarvelApiResponseDTO<Character> getById(
            @PathVariable("id") Integer id,
            @RequestParam(value = "ts") String ts,
            @RequestParam(value = "apikey") String publicKey,
            @RequestParam(value = "hash") String hash
    );
}
