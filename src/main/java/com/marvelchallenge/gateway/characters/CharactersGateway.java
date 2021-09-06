package com.marvelchallenge.gateway.characters;

import com.marvelchallenge.gateway.characters.dto.CharacterDto;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;

public interface CharactersGateway {

    MarvelApiResponse<CharacterDto> getAll(
            String ts,
            String apikey,
            String hash
    );
}
