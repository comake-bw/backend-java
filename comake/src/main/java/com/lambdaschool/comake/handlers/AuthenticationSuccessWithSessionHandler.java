package com.lambdaschool.comake.handlers;

import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;ublic class AuthenticationSuccessWithSessionHandler extends SavedRequestAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler, LogoutSuccessHandler {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletRequest response, Authentication authentication) throws IOException, ServletException
    {
        request.getSession().removeAttribute(USERNAME);
        request.getSession().removeAttribute(PASSWORD);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletRequest response, Authentication authentication) throws IOException, ServletException {
        super.onAu(request, response, authentication);
        request.getSession().setAttribute(PASSWORD, request.getParameter(PASSWORD));
        request.getSession().setAttribute(USERNAME, request.getParameter(USERNAME));
    }
}