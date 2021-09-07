package com.marvelchallenge.presenter.rest.characters;

import com.marvelchallenge.config.SwaggerApiInfo;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(tags = "Characters")
public interface CharactersController extends SwaggerApiInfo {
    @Operation(summary = "Get all characters IDS",
            tags = "Characters",
            responses = @ApiResponse(responseCode = "200", description = "Success"
                    , content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = Integer[].class
                            )
                    ),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo",
                                    value = "[1011334,1017100,1009144,1010699,1009146,1016823,1009148,1009149]"
                            ),
                    }
            )))
    List<Integer> getIds();

    @Operation(summary = "Get character by ID",
            tags = "Characters",
            responses = @ApiResponse(responseCode = "200", description = "Success"
                    , content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = CharacterDTO.class
                            )
                    ),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo",
                                    value = "{\"id\":1010699,\"name\":\"Aaron Stack\",\"modified\":\"1969-12-31T19:00:00-0500\",\"thumbnail\":{\"path\":\"http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available\",\"extension\":\"jpg\"}}"
                            ),
                    }
            )),
            parameters = {
                    @Parameter(name = "language",
                            example = "en",
                            description = "Language to translate",
                            in = ParameterIn.QUERY),
            })
    CharacterDTO getById(@PathVariable("id") Integer id);

}
