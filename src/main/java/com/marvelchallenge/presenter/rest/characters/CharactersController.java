package com.marvelchallenge.presenter.rest.characters;

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

import java.util.List;

@Api(tags = "Characters")
public interface CharactersController {
    @Operation(summary = "Consulta lista de projetos",
            tags = "Projects",
            description = "descri",
            responses = @ApiResponse(responseCode = "200", description = "Sucesso"
                    , content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = Integer.class
                            )
                    ),
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "Exemplo",
                                    value = "{\"content\":[{\"id\":\"507f191e810c19729de860ea\",\"name\":\"Nome do projeto\",\"stages\":[{\"id\":\"507f191e810c19729de860ea\",\"name\":\"Superestrutura\",\"results\":[{\"id\":\"507f191e810c19729de860ea\",\"name\":\"Estrutura de concreto\",\"elements\":[],\"criteria\":[{\"id\":\"507f191e810c19729de860ea\",\"name\":\"Bitola\",\"value\":\"4.2\"}]}]}]}],\"pageable\":{\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"offset\":0,\"pageNumber\":0,\"pageSize\":1,\"paged\":true,\"unpaged\":false},\"last\":true,\"totalPages\":1,\"totalElements\":1,\"sort\":{\"sorted\":false,\"unsorted\":true,\"empty\":true},\"first\":true,\"size\":1,\"number\":0,\"numberOfElements\":1,\"empty\":false}\n"
                            ),
                    }
            )),
            parameters = {
                    @Parameter(name = "name",
                            description = "Buscar proojeto por nome",
                            in = ParameterIn.QUERY),
                    @Parameter(name = "page",
                            description = "Número da página: Inicia pela pagina ZERO",
                            in = ParameterIn.QUERY),
                    @Parameter(name = "size",
                            description = "Quantidade de registros por página",
                            in = ParameterIn.QUERY),
                    @Parameter(name = "sort",
                            description = "Ordenação por parametro: sort=name,asc.",
                            in = ParameterIn.QUERY),
            })
    List<Integer> getIds(QueryParams queryParams);

}
