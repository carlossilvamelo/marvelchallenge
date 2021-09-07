package com.marvelchallenge.presenter.rest.characters.impl;

import com.marvelchallenge.CharacterAdapter;
import com.marvelchallenge.presenter.rest.characters.CharacterDTO;
import com.marvelchallenge.presenter.rest.characters.CharactersController;
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
    public List<Integer> getIds() {

        return getCharactersIds.execute();
    }

    @GetMapping("/{id}")
    public CharacterDTO getById(Integer id) {

        return CharacterAdapter.convert(getCharacterById.execute(id));
    }

}
