package com.dng.cs.core.security.provider;

import com.dng.cs.core.security.CsUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CsBasicAuthProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;
    private final CsUserDetailService csUserDetailService;

    public CsBasicAuthProvider(PasswordEncoder passwordEncoder,
                               CsUserDetailService csUserDetailService) {
        this.passwordEncoder = passwordEncoder;
        this.csUserDetailService = csUserDetailService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("Authenticating user " + authentication.getName());
        UserDetails userDetails = csUserDetailService.loadUserByUsername(authentication.getName());
        Boolean checkPassword = checkingPassword(authentication.getCredentials().toString());
        if (!checkPassword) throw new BadCredentialsException("Wrong Username or password");

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                                       userDetails.getPassword(),
                                                       userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
    }

    private Boolean checkingPassword(String requestPassword) {
        return requestPassword != null;
    }
}
