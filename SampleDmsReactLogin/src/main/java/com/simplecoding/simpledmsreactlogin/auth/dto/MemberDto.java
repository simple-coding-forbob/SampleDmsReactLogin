package com.simplecoding.simpledmsreactlogin.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class MemberDto {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String name;
    private String codeName="ROLE_USER";
    private Long eno;
}
