package com.marvelchallenge.usecase.character.impl;

import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.characters.dto.MarvelApiResponseDTO;
import com.marvelchallenge.models.AuthInfo;
import com.marvelchallenge.models.Character;
import com.marvelchallenge.usecase.auth.GetAuthInfo;
import com.marvelchallenge.usecase.character.GetCharactersIds;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCharactersIdsImpl extends GetAuthInfo implements GetCharactersIds {

    private final CharactersGateway charactersGateway;

    @Override
    public List<Integer> execute() {
        AuthInfo authInfo = getAuthInfo();

        MarvelApiResponseDTO<Character> characterList = charactersGateway
                .getAll(authInfo.getTimestamp()
                        , publicKey
                        , authInfo.getHash());

        List<Character> characterDtoList = characterList.getData().getResults();

        return characterDtoList.stream()
                .map(Character::getId)
                .collect(Collectors.toList());
    }
}
