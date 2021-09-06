package com.marvelchallenge.usecase;

import com.marvelchallenge.models.Character;

public interface GetCharacterById {

    Character execute(Integer id);
}
