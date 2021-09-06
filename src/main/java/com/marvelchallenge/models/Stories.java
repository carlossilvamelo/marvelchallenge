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
public class Stories {
    private Integer available;
    private String collectionURI;
    private List<Item> items;
    private Integer returned;
}
