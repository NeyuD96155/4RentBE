package tech.rent.be.services;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.UserDTO;
import tech.rent.be.entity.Users;
import tech.rent.be.repository.UsersRepository;
import tech.rent.be.utils.AccountUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

}
