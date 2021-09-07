package com.marvelchallenge.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info =
@Info(title = "Marvel API Gateway", description = "The gateway for Marvel API", version = "${api.version}"),
        servers = {
                @Server(url = "https://marvel-api-gateway.herokuapp.com/marvel", description = "HEROKU Enviromment"),
                @Server(url = "http://localhost:8080/marvel", description = "Local Enviromment"),
        })
public interface SwaggerApiInfo {
}
