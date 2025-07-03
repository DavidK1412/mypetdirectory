package com.davidk1412.mypetdirectory.security;

import com.davidk1412.mypetdirectory.entity.User;
import com.davidk1412.mypetdirectory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("No user found"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPasswordHash(), List.of()
        );
    }
}
