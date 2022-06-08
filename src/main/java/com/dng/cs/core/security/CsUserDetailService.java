package com.dng.cs.core.security;

import com.dng.cs.core.entity.RoleEntity;
import com.dng.cs.core.entity.UserEntity;
import com.dng.cs.core.repository.base.RoleBaseRepository;
import com.dng.cs.core.repository.base.UserBaseRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CsUserDetailService implements UserDetailsService {

    private final UserBaseRepository userRepository;
    private final RoleBaseRepository roleRepository;

    public CsUserDetailService(UserBaseRepository userRepository,
                               RoleBaseRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String err;

        UserEntity entity = userRepository.findFirstByUserName(username).orElseThrow(() -> {
            throw new UsernameNotFoundException(String.format("Username [%s] not found", username));
        });

        List<RoleEntity> userRoles = roleRepository.findAllByUserId(entity.getId());
        Collection<? extends GrantedAuthority> authorities = userRoles.stream()
                                                                      .map(m -> new SimpleGrantedAuthority(m.getRoleName()))
                                                                      .collect(Collectors.toList());
        return User.builder().username(username)
                             .password(entity.getPassword())
                             .authorities(authorities)
                             .build();
    }
}
