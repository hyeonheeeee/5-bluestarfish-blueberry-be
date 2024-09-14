package com.bluestarfish.blueberry.auth.controller;

import com.bluestarfish.blueberry.auth.dto.LoginRequest;
import com.bluestarfish.blueberry.auth.dto.LoginSuccessResult;
import com.bluestarfish.blueberry.auth.dto.MailAuthRequest;
import com.bluestarfish.blueberry.auth.dto.MailRequest;
import com.bluestarfish.blueberry.auth.service.AuthService;
import com.bluestarfish.blueberry.common.dto.ApiSuccessResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.bluestarfish.blueberry.common.handler.ResponseHandler.handleSuccessResponse;
import static com.bluestarfish.blueberry.util.CookieCreator.createAuthCookie;
import static com.bluestarfish.blueberry.util.CookieCreator.removeAuthCookie;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiSuccessResponse<?> login(
            @RequestBody LoginRequest loginRequest,
            HttpServletResponse response
    ) {
        LoginSuccessResult loginSuccessResult = authService.login(loginRequest);
        response.addCookie(createAuthCookie(loginSuccessResult.getAccessToken()));

        return handleSuccessResponse(HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ApiSuccessResponse<?> logout(
            @CookieValue("Authorization") String accessToken,
            HttpServletResponse response
    ) {
        authService.logout(accessToken);
        response.addCookie(removeAuthCookie());
        
        return handleSuccessResponse(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/mail")
    public ApiSuccessResponse<?> sendMail(
            @RequestBody MailRequest mailRequest
    ) {
        authService.sendMail(mailRequest);
        return handleSuccessResponse(HttpStatus.CREATED);
    }

    @GetMapping("/mail")
    public ApiSuccessResponse<?> authenticateCode(
            MailAuthRequest mailAuthRequest
    ) {
        authService.authenticateCode(mailAuthRequest);
        return handleSuccessResponse(HttpStatus.OK);
    }
}
