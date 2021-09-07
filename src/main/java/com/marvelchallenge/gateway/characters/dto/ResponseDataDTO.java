package com.marvelchallenge.gateway.characters.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseDataDTO<T> {
    private Integer offset;
    private Integer limit;
    private Integer total;
    private Integer count;
    private List<T> results;
}
