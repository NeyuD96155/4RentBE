package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.RegisterRequestDTO;
import tech.rent.be.entity.Users;
import tech.rent.be.repository.UsersRepository;

@Service
public class AuthenticationService {
    @Autowired
    UsersRepository usersRepository;
    public Users register(Users users){
        return usersRepository.save(users);
    }
}
