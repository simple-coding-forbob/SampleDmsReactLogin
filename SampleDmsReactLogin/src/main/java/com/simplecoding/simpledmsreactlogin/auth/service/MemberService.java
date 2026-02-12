package com.simplecoding.simpledmsreactlogin.auth.service;


import com.simplecoding.simpledmsreactlogin.auth.dto.JwtDto;
import com.simplecoding.simpledmsreactlogin.auth.dto.MemberDto;
import com.simplecoding.simpledmsreactlogin.auth.dto.MypageDto;
import com.simplecoding.simpledmsreactlogin.auth.entity.Member;
import com.simplecoding.simpledmsreactlogin.auth.repository.MemberRepository;
import com.simplecoding.simpledmsreactlogin.common.CommonUtil;
import com.simplecoding.simpledmsreactlogin.common.MapStruct;
import com.simplecoding.simpledmsreactlogin.common.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MapStruct mapStruct;
    private final PasswordEncoder passwordEncoder;
    private final CommonUtil commonUtil;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtUtils jwtUtils;

    public void save(MemberDto memberDto) {
        if (memberRepository.existsById(memberDto.getEmail())) {
            throw new RuntimeException(commonUtil.getMessage("errors.register"));
        }
        String encodedPassword = passwordEncoder.encode(memberDto.getPassword());

        memberDto.setPassword(encodedPassword);
        Member member=mapStruct.toEntity(memberDto);
        memberRepository.save(member);
    }

    public JwtDto login(MemberDto memberDto) {

        Authentication authentication
                = authenticationManagerBuilder.getObject().authenticate(
                new UsernamePasswordAuthenticationToken(memberDto.getEmail(),
                        memberDto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.generateJwtToken(authentication);
        JwtDto jwtDto = new JwtDto(
                accessToken
        );
        return jwtDto;
    }

    public MypageDto findById(String email) {
        Member member = memberRepository.findById(email)
                .orElseThrow(() -> new RuntimeException(commonUtil.getMessage("errors.not.found")));

        return mapStruct.toDto2(member);
    }
}
