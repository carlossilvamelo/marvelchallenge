package com.marvelchallenge.usecase.translation;

import com.marvelchallenge.models.Character;

public interface GetTranslations {
    void execute(Character character, String to);
}
