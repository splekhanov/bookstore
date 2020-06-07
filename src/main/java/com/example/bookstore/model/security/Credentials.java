package com.example.bookstore.model.security;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Credentials {

    @ApiModelProperty(value = "User name", position = 1)
    private String username;

    @ApiModelProperty(value = "User password", position = 2)
    private String password;

}
