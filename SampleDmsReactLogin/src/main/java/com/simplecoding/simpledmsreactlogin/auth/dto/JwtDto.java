package com.simplecoding.simpledmsreactlogin.auth.dto;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class JwtDto {
    private String accessToken;
    private final String tokenType = "Bearer";
}
