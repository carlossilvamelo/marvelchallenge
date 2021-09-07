package com.marvelchallenge.usecase.character;

import com.marvelchallenge.models.Character;

public interface GetCharacterById {

    Character execute(Integer id, String lang);
}
