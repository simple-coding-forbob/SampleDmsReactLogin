package com.simplecoding.simpledmsreactlogin.auth.entity;


import com.simplecoding.simpledmsreactlogin.common.BaseTimeEntity;
import com.simplecoding.simpledmsreactlogin.emp.entity.Emp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="TB_MEMBER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member extends BaseTimeEntity {
    @Id
    private String email;
    private String password;
    private String name;
    private String codeName="ROLE_USER";
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eno")
    private Emp emp;
}
