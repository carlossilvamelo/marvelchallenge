package com.marvelchallenge.presenter.rest.characters;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryParams {

    @Schema(description = "publicKey")
    private String publicKey;

    @Schema(description = "privateKey")
    private String privateKey;
}
