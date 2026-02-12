package com.simplecoding.simpledmsreactlogin.emp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmpDto {
    private Long eno;
    @NotBlank
    private String ename;
    @NotBlank
    private String job;
    private Long manager;
    private LocalDate hiredate;
    private Long salary;
    private Long commission;
    private Long dno;
}
