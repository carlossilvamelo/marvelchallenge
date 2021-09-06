package com.marvelchallenge.usecase.impl;

import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import com.marvelchallenge.models.Character;
import com.marvelchallenge.usecase.GetCharactersIds;
import com.marvelchallenge.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCharactersIdsImpl implements GetCharactersIds {

    @Value("${api-keys.public}")
    private String publicKey;
    @Value("${api-keys.private}")
    private String privateKey;

    private final CharactersGateway charactersGateway;

    @Override
    public List<Integer> execute() {
        final String timestamp = LocalDateTime.now().toString();
        final String hash = TokenUtils.generateHashBy(
                timestamp
                , privateKey
                , publicKey
        );

        MarvelApiResponse<Character> respose = charactersGateway
                .getAll(timestamp
                        , publicKey
                        , hash);

        List<Character> characterDtos = respose.getData().getResults();

        return characterDtos.stream()
                .map(Character::getId)
                .collect(Collectors.toList());
    }
}
