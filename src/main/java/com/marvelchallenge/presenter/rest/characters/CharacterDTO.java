package com.marvelchallenge.presenter.rest.characters;

import com.marvelchallenge.models.Thumbnail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CharacterDTO {
    private Integer id;
    private String name;
    private String description;
    private Thumbnail thumbnail;
}
