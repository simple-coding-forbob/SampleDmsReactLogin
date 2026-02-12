package com.simplecoding.simpledmsreactlogin.dept.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeptDto {
    private Long dno;
    @NotBlank
    private String  dname;
    @NotBlank
    private String  loc;
}
