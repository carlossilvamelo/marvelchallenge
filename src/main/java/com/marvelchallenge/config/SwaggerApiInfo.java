package com.marvelchallenge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info =
@Info(title = "Execut API", description = "Execut API", version = "${api.version}"),
        servers = {
                @Server(url = "https://execut-api.herokuapp.com/marvel", description = "Enviromment HEROKU"),
                @Server(url = "http://localhost:8081/execut", description = "Enviromment Local"),
        })
public interface SwaggerApiInfo {
}
