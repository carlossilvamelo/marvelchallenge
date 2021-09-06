package com.marvelchallenge.gateway.characters;

import com.marvelchallenge.gateway.characters.dto.CharacterDto;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharactersGatewayImpl implements CharactersGateway {

    private final CharactersClient charactersClient;

    @Override
    public MarvelApiResponse<CharacterDto> getAll(String ts
            , String publicKey
            , String hash) {
        return charactersClient.getAll(
                ts
                , publicKey
                , hash);
    }
}
