package com.marvelchallenge.gateway.characters;

import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.marvelchallenge.models.Character;
@Component
@RequiredArgsConstructor
public class CharactersGatewayImpl implements CharactersGateway {

    private final CharactersClient charactersClient;

    @Override
    public MarvelApiResponse<Character> getAll(String ts
            , String publicKey
            , String hash) {
        return charactersClient.getAll(
                ts
                , publicKey
                , hash);
    }

    @Override
    public MarvelApiResponse<Character> getById(Integer id
            , String ts
            , String apikey
            , String hash) {

        return charactersClient.getById(id
                , ts
                , apikey
                , hash);
    }
}
