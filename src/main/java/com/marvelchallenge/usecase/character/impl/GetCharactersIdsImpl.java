package com.marvelchallenge.usecase.character.impl;

import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;
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

        MarvelApiResponse<Character> respose = charactersGateway
                .getAll(authInfo.getTimestamp()
                        , publicKey
                        , authInfo.getHash());

        List<Character> characterDtos = respose.getData().getResults();

        return characterDtos.stream()
                .map(Character::getId)
                .collect(Collectors.toList());
    }
}
