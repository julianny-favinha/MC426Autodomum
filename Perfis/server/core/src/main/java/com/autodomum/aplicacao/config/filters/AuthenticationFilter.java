package com.autodomum.aplicacao.config.filters;

import com.autodomum.service.usuario.AuthenticationService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * @author sabrina on 26/05/16.
 */
public class AuthenticationFilter implements Filter {

    private final AuthenticationService authenticationService;

    public AuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getCookies() == null) {
            sendError(httpResponse);
            return;
        }

        Optional<Cookie> authCookie = Arrays.stream(httpRequest.getCookies())
                .filter(c -> c.getName().equals(AuthenticationService.AUTH_COOKIE))
                .findFirst();

        if (!authCookie.isPresent()) {
            sendError(httpResponse);
            return;
        }

        String token = authCookie.get().getValue();

        if(!authenticationService.isValidToken(token)) {
            sendError(httpResponse);
            return;
        }

        filterChain.doFilter(request, response);

    }

    private void sendError(HttpServletResponse httpResponse) throws IOException {
        httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Please, log in.");
    }

    @Override
    public void destroy() {

    }
}
