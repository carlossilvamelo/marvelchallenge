package com.marvelchallenge.adapter;

import com.marvelchallenge.presenter.rest.characters.dtos.CharacterDTO;
import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;
import com.marvelchallenge.models.Character;

@UtilityClass
public class CharacterAdapter {

    public static CharacterDTO convert(Character character){
        return new ModelMapper().map(character, CharacterDTO.class);
    }
}
