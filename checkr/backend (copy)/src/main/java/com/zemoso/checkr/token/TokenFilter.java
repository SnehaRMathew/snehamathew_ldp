package com.zemoso.checkr.token;


import com.zemoso.checkr.service.TokenDecodeService;
import com.zemoso.checkr.service.TokenEncodeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class TokenFilter extends GenericFilterBean {

    private final TokenDecodeService tokenDecodeService;
    public TokenFilter(TokenDecodeService tokenDecodeService){
        this.tokenDecodeService = tokenDecodeService;
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return new String();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = extractToken((HttpServletRequest)servletRequest);
        System.out.println("I reached here" +token);
        if (!token.isEmpty()) {
            // Authentication authentication =
            tokenDecodeService.getUsernameFromToken(token);
            //SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
