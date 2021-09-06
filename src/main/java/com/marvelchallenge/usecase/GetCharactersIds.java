package com.marvelchallenge.usecase;

import com.marvelchallenge.presenter.rest.characters.QueryParams;

import java.util.List;

public interface GetCharactersIds {

    List<Integer> execute(QueryParams queryParams);
}
