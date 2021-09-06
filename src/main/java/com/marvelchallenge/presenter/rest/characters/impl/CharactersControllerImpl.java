package com.marvelchallenge.presenter.rest.characters.impl;

import com.marvelchallenge.presenter.rest.characters.CharactersController;
import com.marvelchallenge.presenter.rest.characters.QueryParams;
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

    @GetMapping
    public List<Integer> getIds(QueryParams queryParams) {
        return getCharactersIds.execute(queryParams);
    }
}
