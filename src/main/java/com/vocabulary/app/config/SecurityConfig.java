package com.vocabulary.app.config;

import com.vocabulary.app.security.jwt.JwtConfigurer;
import com.vocabulary.app.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    private static final String AUTH = "/auth/**";

    private static final String LOGIN_PAGE = "/login";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .sessionManagement().invalidSessionUrl("/login")
                .sessionAuthenticationErrorUrl("/login")
                .and()

                .formLogin()
                .loginPage("/login")
                .successForwardUrl("/auth/users/{token}/{userName}")
                .permitAll().loginProcessingUrl("/auth/users/{token}/{userName}")
                .and()
                .logout()

                .deleteCookies("remove")
                .permitAll()
                .and()
                .authorizeRequests()
//                .antMatchers("/auth/**").hasAnyAuthority("ADMIN","USER")
//                .and()
                .antMatchers(LOGIN_PAGE).permitAll()
                .antMatchers(AUTH).hasAnyAuthority("ADMIN","USER")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
