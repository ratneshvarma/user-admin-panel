package com.panel.security;

import com.panel.dto.JwtAuthenticationTokenDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.panel.constant.SecurityConstants.HEADER_VALUE;
import static com.panel.constant.SecurityConstants.TOKEN_MISSING_ERROR;
import static com.panel.constant.SecurityConstants.TOKEN_PREFIX;
import static com.panel.constant.SecurityConstants.TOKEN_PREFIX_LENGHT;
import static com.panel.constant.SecurityConstants.URL_TO_FILTER;

import java.io.IOException;;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {
    public JwtAuthenticationTokenFilter() {
        super(URL_TO_FILTER);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String header = httpServletRequest.getHeader(HEADER_VALUE);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            throw new RuntimeException(TOKEN_MISSING_ERROR);
        }
        String authenticationToken = header.substring(TOKEN_PREFIX_LENGHT);
        JwtAuthenticationTokenDto token = new JwtAuthenticationTokenDto(authenticationToken);
        return getAuthenticationManager().authenticate(token);
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);
        chain.doFilter(request, response);
    }
}
