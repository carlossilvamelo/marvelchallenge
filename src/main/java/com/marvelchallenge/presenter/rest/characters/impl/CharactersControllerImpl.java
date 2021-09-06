package com.marvelchallenge.presenter.rest.characters.impl;

import com.marvelchallenge.models.Character;
import com.marvelchallenge.presenter.rest.characters.CharactersController;
import com.marvelchallenge.presenter.rest.characters.QueryParams;
import com.marvelchallenge.usecase.GetCharacterById;
import com.marvelchallenge.usecase.GetCharactersIds;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("characters")
@RequiredArgsConstructor
public class CharactersControllerImpl implements CharactersController {

    private final GetCharactersIds getCharactersIds;
    private final GetCharacterById getCharacterById;

    @GetMapping
    public List<Integer> getIds(QueryParams queryParams) {
        return getCharactersIds.execute(queryParams);
    }

    @GetMapping("/{id}")
    public Character getById(Integer id, QueryParams queryParams) {
        return getCharacterById.execute(id, queryParams);
    }

}
