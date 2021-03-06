package com.marvelchallenge.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Character {
    private Integer id;
    private String name;
    private String description;
    private Thumbnail thumbnail;
    private Comics comics;
    private Series series;
    private Stories stories;
    private Events events;
    private List<Url> urls;
}
