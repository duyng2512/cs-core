package com.dng.cs.core.security.config;

import com.dng.cs.core.security.filter.ApiKeyFilter;
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

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@Slf4j
@Profile("basic")
public class CsSecurityBasicConfig extends WebSecurityConfigurerAdapter {

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

        http.addFilterBefore(new ApiKeyFilter(), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement()
                .sessionFixation()
                .migrateSession()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true);

        http.authorizeRequests()
                .antMatchers("/auth/**")
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .rememberMe()
                .key("REMEMBER_ME")
                .tokenValiditySeconds(3600);

        http.authorizeRequests()
                .antMatchers(EndpointConstants.secureEndpoint)
                .authenticated();

        http.csrf()
                .disable()
                .logout(logout -> logout
                        .logoutUrl("/auth/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            log.info("Invalidate " + Arrays.toString(request.getCookies()));
                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
    }
}