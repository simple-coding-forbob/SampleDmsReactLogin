package com.simplecoding.simpledmsreactlogin.common.jwt;

import com.simplecoding.simpledmsreactlogin.auth.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * JWT 기반 인증 필터
 * 역할:
 * 1) 요청 헤더에서 JWT 토큰 추출
 * 2) 토큰 유효성 검증 (위조, 만료 등)
 * 3) DB에서 사용자 상세 정보 조회 (UserDetailsService 사용)
 * 4) 인증 객체 생성 및 SecurityContext에 저장
 * 5) 다음 필터로 체인 전달
 */
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;                         // JWT 처리 유틸리티

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // DB에서 사용자 상세조회용 서비스

    /**
     * 실제 필터 처리 로직
     * 모든 요청마다 한 번씩 실행됨
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            Optional<String> jwt = jwtUtils.getJwtFromCookies(request);
            if (jwt.isPresent() && jwtUtils.validateJwtToken(jwt.get())) {
                String email = jwtUtils.getUserNameFromJwt(jwt.get());
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            log.error("JWT 인증 처리 중 오류 발생: ", e);
        }
        filterChain.doFilter(request, response);
    }
}
