package com.example.bookstore.model.security;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Credentials {

    @ApiModelProperty(value = "User name", position = 1)
    @NotBlank(message = "Email is mandatory")
    @Schema(description = "User e-mail as login",
            example = "admin@epam.com", required = true)
    private String email;

    @ApiModelProperty(value = "User password", position = 2)
    @NotBlank(message = "Password is mandatory")
    @Schema(description = "User password",
            example = "1234", required = true)
    private String password;

}
