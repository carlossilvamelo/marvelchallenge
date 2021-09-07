package com.marvelchallenge.usecase.character.impl;

import com.marvelchallenge.exception.ResourceNotFoundException;
import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.characters.dto.MarvelApiResponseDTO;
import com.marvelchallenge.gateway.characters.dto.ResponseDataDTO;
import com.marvelchallenge.models.AuthInfo;
import com.marvelchallenge.models.Character;
import com.marvelchallenge.usecase.auth.GetAuthInfo;
import com.marvelchallenge.usecase.character.GetCharacterById;
import com.marvelchallenge.usecase.translation.GetTranslations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCharacterByIdImpl extends GetAuthInfo implements GetCharacterById {

    private final CharactersGateway charactersGateway;
    private final GetTranslations getTranslations;

    @Override
    public Character execute(Integer id, String lang) {

        AuthInfo authInfo = getAuthInfo();

        MarvelApiResponseDTO<Character> characterDtoList = charactersGateway.getById(id
                , authInfo.getTimestamp()
                , publicKey
                , authInfo.getHash());
        Character character = getIfExists(characterDtoList);
        getTranslations.execute(character, lang);
        return character;

    }

    private Character getIfExists(MarvelApiResponseDTO<Character> characterDtoList) {
        characterDtoList = Optional.ofNullable(characterDtoList)
                .orElseThrow(ResourceNotFoundException::new);
        ResponseDataDTO<Character> data = Optional.ofNullable(characterDtoList.getData())
                .orElseThrow(ResourceNotFoundException::new);
        List<Character> results = data.getResults();
        return results.stream()
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
