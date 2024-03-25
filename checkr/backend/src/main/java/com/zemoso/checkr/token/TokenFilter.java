package com.zemoso.checkr.token;


import com.zemoso.checkr.service.impl.TokenDecodeServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TokenFilter extends GenericFilterBean {

    private final TokenDecodeServiceImp tokenDecodeServiceImp;
    Logger logger=Logger.getLogger(TokenFilter.class.getName());
    public TokenFilter(TokenDecodeServiceImp tokenDecodeServiceImp){
        this.tokenDecodeServiceImp = tokenDecodeServiceImp;
    }

    private String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return "";
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String token = extractToken((HttpServletRequest)servletRequest);
        logger.log(Level.INFO,"I reached here" +token);
        if (!token.isEmpty()) {
            // Authentication authentication =
            tokenDecodeServiceImp.getUsernameFromToken(token);
            //SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
