package com.marvelchallenge.usecase;

import com.marvelchallenge.models.Character;
import com.marvelchallenge.presenter.rest.characters.QueryParams;

public interface GetCharacterById {

    Character execute(Integer id, QueryParams queryParams);
}
