package com.dng.cs.core.security.config;

import com.dng.cs.core.security.filter.JwtTokenFilter;
import com.dng.cs.core.security.provider.CsBasicAuthProvider;
import com.dng.cs.core.security.util.EndpointConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Slf4j
@Profile("token")
public class CsSecurityTokenConfig extends WebSecurityConfigurerAdapter {

    private CsBasicAuthProvider authenticationProvider;

    @Autowired
    public void setAuthenticationProvider(CsBasicAuthProvider provider){
        authenticationProvider = provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Enable CORS and disable CSRF
        http.cors().and().csrf().disable();

        // Set session management to stateless
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http.authorizeRequests()
            .antMatchers("/auth/**")
            .authenticated()
            .and()
            .httpBasic();

        http.authorizeRequests()
            .antMatchers(EndpointConstants.secureEndpoint)
            .permitAll();

        http.addFilterBefore(new JwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}