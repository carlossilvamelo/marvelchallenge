package com.marvelchallenge.presenter.rest.characters;

import com.marvelchallenge.config.SwaggerApiInfo;
import com.marvelchallenge.exception.config.StandardError;
import com.marvelchallenge.presenter.rest.characters.dto.CharacterDTO;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Api(tags = "Characters")
public interface CharactersController extends SwaggerApiInfo {
    @Operation(summary = "Get all characters IDS",
            tags = "Characters",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success"
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
                    )),
                    @ApiResponse(responseCode = "500", description = "Business Rule Exception"
                            , content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = StandardError.class
                                    )
                            ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo",
                                            value = "{\n" +
                                                    "  \"timestamp\": \"2021-09-07 08:08:50\",\n" +
                                                    "  \"status\": 500,\n" +
                                                    "  \"error\": \"INTERNAL_SERVER_ERROR\",\n" +
                                                    "  \"message\": \"Error when trying to call the external API\",\n" +
                                                    "  \"path\": \"/marvel/characters/1010699\",\n" +
                                                    "  \"exceptionCode\": \"BUSINESS_ERROR\"\n" +
                                                    "}"
                                    ),
                            }
                    ))
            })
    List<Integer> getIds();

    @Operation(summary = "Get character by ID",
            tags = "Characters",
            responses = {@ApiResponse(responseCode = "200", description = "Success"
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
            )),@ApiResponse(responseCode = "500", description = "Business Rule Exception"
                            , content = @Content(
                            array = @ArraySchema(
                                    schema = @Schema(
                                            implementation = StandardError.class
                                    )
                            ),
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo",
                                            value = "{\n" +
                                                    "  \"timestamp\": \"2021-09-07 08:08:50\",\n" +
                                                    "  \"status\": 500,\n" +
                                                    "  \"error\": \"INTERNAL_SERVER_ERROR\",\n" +
                                                    "  \"message\": \"Error when trying to translate by code language ´asd´\",\n" +
                                                    "  \"path\": \"/marvel/characters/1010699\",\n" +
                                                    "  \"exceptionCode\": \"BUSINESS_ERROR\"\n" +
                                                    "}"
                                    ),
                            }
                    )),@ApiResponse(responseCode = "404", description = "Resource not found"
                    , content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = StandardError.class
                            )
                    ),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo",
                                    value = "{\n" +
                                            "  \"timestamp\": \"2021-09-07 08:08:50\",\n" +
                                            "  \"status\": 404,\n" +
                                            "  \"error\": \"RESOURCE_NOT_FOUND\",\n" +
                                            "  \"message\": \"Resource not found \",\n" +
                                            "  \"path\": \"/marvel/characters/1010699\",\n" +
                                            "  \"exceptionCode\": \"RESOURCE_NOT_FOUND\"\n" +
                                            "}"
                            ),
                    }
            ))},
            parameters = {
                    @Parameter(name = "language",
                            description = "Language to translate",
                            in = ParameterIn.QUERY),
            })
    CharacterDTO getById(@PathVariable("id") Integer id
            , @RequestParam(name = "language", defaultValue = "en") String lang);

}
