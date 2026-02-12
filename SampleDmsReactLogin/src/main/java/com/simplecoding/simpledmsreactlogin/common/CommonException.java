package com.simplecoding.simpledmsreactlogin.common;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class CommonException {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse> handleResponseStatusException(ResponseStatusException e) {
        log.error("에러 발생: {}", e);

        ApiResponse response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(e.getMessage());

        return ResponseEntity.status(e.getStatusCode()).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleAllException(Exception e) {
        log.error("벡엔드 서버 오류가 발생했습니다.", e);

        ApiResponse response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(e.getMessage());

        return ResponseEntity.status(500).body(response);
    }
}
