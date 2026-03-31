package com.simplecoding.simpledmsreactlogin.interceptor;

import com.simplecoding.simpledmsreactlogin.common.CommonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {
    private final CommonUtil util;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = util.getLoginUser();
        if (loginUser == null) {
            // 로그인 안 됨 → 예외 던지기
            throw new RuntimeException(util.getMessage("errors.unauthorized"));
        }

        return true; // 로그인 되어 있으면 요청 계속 진행
    }
}
