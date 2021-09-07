package com.marvelchallenge.gateway.characters.impl;

import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.characters.client.CharactersClient;
import com.marvelchallenge.gateway.characters.dto.MarvelApiResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.marvelchallenge.models.Character;

@Component
@RequiredArgsConstructor
public class CharactersGatewayImpl implements CharactersGateway {

    private final CharactersClient charactersClient;

    @Override
    public MarvelApiResponseDTO<Character> getAll(String ts
            , String publicKey
            , String hash) {
        return charactersClient.getAll(
                ts
                , publicKey
                , hash);
    }

    @Override
    public MarvelApiResponseDTO<Character> getById(Integer id
            , String ts
            , String apikey
            , String hash) {

        return charactersClient.getById(id
                , ts
                , apikey
                , hash);
    }
}
