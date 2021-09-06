package com.marvelchallenge.gateway.characters;

import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import com.marvelchallenge.models.Character;

public interface CharactersGateway {

    MarvelApiResponse<Character> getAll(
            String ts,
            String apikey,
            String hash
    );

    MarvelApiResponse<Character> getById(
            Integer id,
            String ts,
            String apikey,
            String hash
    );
}
