package com.simplecoding.simpledmsreactlogin.auth.service;


import com.simplecoding.simpledmsreactlogin.auth.dto.SecurityUserDto;
import com.simplecoding.simpledmsreactlogin.auth.entity.Member;
import com.simplecoding.simpledmsreactlogin.auth.repository.MemberRepository;
import com.simplecoding.simpledmsreactlogin.common.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final CommonUtil commonUtil;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member
                = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException(commonUtil.getMessage("errors.not.found")));

        GrantedAuthority authority = new SimpleGrantedAuthority(member.getCodeName());

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(authority);

        return new SecurityUserDto(member.getEmail(),
                member.getPassword(),
                authorities,
                member.getEmp().getEno()
        );
    }
}
