package com.kcommerce.domain.member.controller;

import com.kcommerce.domain.member.domain.Member;
import com.kcommerce.domain.member.dto.AuthDto;
import com.kcommerce.domain.member.mapper.AuthMapper;
import com.kcommerce.global.config.security.JwtProperties;
import com.kcommerce.global.config.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final AuthMapper authMapper;

    @PostMapping
    public ResponseEntity<AuthDto.Response> auth(@RequestBody @Valid AuthDto.Request request) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String accessToken = jwtProvider.createToken(authentication, JwtProperties.ACCESS_TOKEN_TIME);

        String refreshToken = jwtProvider.createToken(authentication, JwtProperties.REFRESH_TOKEN_TIME);
        ResponseCookie cookie = ResponseCookie.from(JwtProperties.REFRESH_TOKEN_COOKIE, refreshToken)
                .maxAge(Duration.ofMillis(JwtProperties.REFRESH_TOKEN_TIME))
                .httpOnly(true)
                .build();

        AuthDto.MemberAdapter principal = (AuthDto.MemberAdapter) authentication.getPrincipal();
        Member member = principal.getMember();

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(authMapper.toResponse(member));
    }
}
