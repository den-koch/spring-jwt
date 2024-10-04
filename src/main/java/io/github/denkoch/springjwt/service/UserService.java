package io.github.denkoch.springjwt.service;

import io.github.denkoch.springjwt.entity.User;
import io.github.denkoch.springjwt.repository.UserRepository;
import io.github.denkoch.springjwt.security.jwt.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtProvider jwtProvider;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public String verify(User user) {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        if (authentication.isAuthenticated())
            return jwtProvider.createToken(user.getEmail());
        return "Fail";
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}