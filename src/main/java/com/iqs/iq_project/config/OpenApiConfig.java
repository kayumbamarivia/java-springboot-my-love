package com.iqs.iq_project.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
info = @Info(
    contact = @Contact(
        name = "@J88 Backend",
        email = "kayumbaj88",
        url = "https://rca.ac.rw"
    ),
    description = "OpenApi Documentation for My Java Backend Template.",
    title = "OpenApi Specification - @J88",
    version = "1.0.0",
    license = @License(
        name = "RCA-License",
        url = "https://rca.ac.rw"
    ),
    termsOfService = "Terms of Service."
),
servers = {
    @Server(
        description = "Local or Dev env",
        url = "http://localhost:8000"
    )
},
security = {
    @SecurityRequirement(
        name = "bearerAuth"
    )
}
)

@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth description",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)

public class OpenApiConfig {

}
