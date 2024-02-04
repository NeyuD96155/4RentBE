package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.LoginRequestDTO;
import tech.rent.be.dto.RegisterRequestDTO;
import tech.rent.be.entity.Users;
import tech.rent.be.repository.UsersRepository;

@Service
public class AuthenticationService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    public Users register(Users users){
        String rawPassword = users.getPassword();
        users.setPassword(passwordEncoder.encode(rawPassword));
        //encode
        return usersRepository.save(users);
    }
    public Users login(LoginRequestDTO loginRequestDTO){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUsername(),
                    loginRequestDTO.getPassword()
            ));
            // acoount chuan
            return (Users) authentication.getPrincipal();
        }catch (Exception e){
            throw new BadCredentialsException("Username or password invalid");
        }
    }
}
