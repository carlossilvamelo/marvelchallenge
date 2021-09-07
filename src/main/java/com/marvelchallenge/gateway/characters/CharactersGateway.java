package com.marvelchallenge.gateway.characters;

import com.marvelchallenge.gateway.characters.dto.MarvelApiResponseDTO;
import com.marvelchallenge.models.Character;

public interface CharactersGateway {

    MarvelApiResponseDTO<Character> getAll(
            String ts,
            String apikey,
            String hash
    );

    MarvelApiResponseDTO<Character> getById(
            Integer id,
            String ts,
            String apikey,
            String hash
    );
}
