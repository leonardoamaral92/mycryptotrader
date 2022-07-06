package com.lmarques.mycryptotrader.security.jwt;

import com.google.gson.Gson;
import com.lmarques.mycryptotrader.exception.ExceptionResponse;
import com.lmarques.mycryptotrader.exception.InvalidJwtAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtTokenFilter extends GenericFilterBean {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                if (auth != null)
                    SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        catch (InvalidJwtAuthenticationException e){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            ExceptionResponse exceptionResponse = ExceptionResponse
                    .builder()
                    .message(e.getMessage())
                    .timestamp(new Date())
                    .build();
            Gson gson = new Gson();
            httpResponse.getWriter().write(gson.toJson(exceptionResponse));
            httpResponse.getWriter().flush();
            return;
        }
        chain.doFilter(request, response);
    }
}
