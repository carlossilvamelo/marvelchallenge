package com.marvelchallenge.usecase.impl;

import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import com.marvelchallenge.presenter.rest.characters.QueryParams;
import com.marvelchallenge.usecase.GetCharactersIds;
import com.marvelchallenge.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.marvelchallenge.models.Character;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetCharactersIdsImpl implements GetCharactersIds {

    private final CharactersGateway charactersGateway;

    @Override
    public List<Integer> execute(QueryParams queryParams) {
        final String timestamp = LocalDateTime.now().toString();
        final String hash = TokenUtils.generateHashBy(
                timestamp
                , queryParams.getPrivateKey()
                , queryParams.getPublicKey()
        );

        MarvelApiResponse<Character> respose = charactersGateway
                .getAll(timestamp
                        , queryParams.getPublicKey()
                        , hash);

        List<Character> characterDtos = respose.getData().getResults();
        return characterDtos.stream()
                .map(Character::getId)
                .collect(Collectors.toList());
    }
}
