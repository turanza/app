package com.vocabulary.app.security.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

private JwtTokenProvider jwtTokenProvider;

public JwtTokenFilter (JwtTokenProvider jwtTokenProvider){
    this.jwtTokenProvider = jwtTokenProvider;

}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        System.out.println(((HttpServletRequest) req).getRequestURI());//Bearer_
        String [] temp = ((HttpServletRequest) req).getRequestURI().split("/");
        System.out.println("lenght temp= "+temp.length);
        String token=null;
        if (temp.length > 2){
            int i = (temp.length)-2;
            token = temp[i];
        }
        //String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication auth = jwtTokenProvider.getAuthentication(token);

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(req, res);
    }

}
