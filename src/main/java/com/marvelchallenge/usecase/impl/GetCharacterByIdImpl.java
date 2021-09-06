package com.marvelchallenge.usecase.impl;

import com.marvelchallenge.exception.ResourceNotFoundException;
import com.marvelchallenge.gateway.characters.CharactersGateway;
import com.marvelchallenge.gateway.dto.MarvelApiResponse;
import com.marvelchallenge.gateway.dto.ResponseData;
import com.marvelchallenge.models.Character;
import com.marvelchallenge.usecase.GetCharacterById;
import com.marvelchallenge.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetCharacterByIdImpl implements GetCharacterById {

    @Value("${api-keys.public}")
    private String publicKey;
    @Value("${api-keys.private}")
    private String privateKey;

    private final CharactersGateway charactersGateway;

    @Override
    public Character execute(Integer id) {
        final String timestamp = LocalDateTime.now().toString();
        final String hash = TokenUtils.generateHashBy(
                timestamp
                , privateKey
                , publicKey
        );

        MarvelApiResponse<Character> respose = charactersGateway.getById(id
                , timestamp
                , publicKey
                , hash);
        return validateAndGetExistent(respose);

    }

    private Character validateAndGetExistent(MarvelApiResponse<Character> respose) {
        respose = Optional.ofNullable(respose)
                .orElseThrow(ResourceNotFoundException::new);
        ResponseData<Character> data = Optional.ofNullable(respose.getData())
                .orElseThrow(ResourceNotFoundException::new);
        List<Character> results = data.getResults();
        return results.stream()
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }
}
