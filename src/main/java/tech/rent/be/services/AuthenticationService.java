package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.EmailDetail;
import tech.rent.be.dto.LoginRequestDTO;
import tech.rent.be.dto.LoginResponseDTO;
import tech.rent.be.dto.RegisterRequestDTO;
import tech.rent.be.entity.Users;
import tech.rent.be.enums.AccountStatus;
import tech.rent.be.exception.DuplicateException;
import tech.rent.be.exception.InValidToken;
import tech.rent.be.repository.UsersRepository;
import tech.rent.be.utils.TokenHandler;

@Service
public class AuthenticationService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenHandler tokenHandler;
    @Autowired
    EmailService emailService;

    public Users register(Users users) {
        String rawPassword = users.getPassword();
        users.setPassword(passwordEncoder.encode(rawPassword));

        users.setStatus(AccountStatus.IN_ACTIVATED);
        // encode
        if (usersRepository.findByUsername(users.getUsername()).isPresent()) {
            throw new DuplicateException("Tên đăng nhập bị trùng ");
        }
        try {
            Users user = usersRepository.save(users);
            EmailDetail emailDetail = new EmailDetail();
            emailDetail.setUser(user);
            emailDetail.setSubject("Xác thực Email");
            emailService.sendEmail(emailDetail);
            return user;

        } catch (DataIntegrityViolationException e) {

            throw new DuplicateException("Email đã tồn tại");
        }
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        Users users = null;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequestDTO.getUsername(),
                    loginRequestDTO.getPassword()

            ));
            // acoount chuan
            users = (Users) authentication.getPrincipal();
            if (users.getStatus() == AccountStatus.IN_ACTIVATED) {
                throw new BadCredentialsException("Tài khoản chưa được kích hoạt!!");
            }
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            loginResponseDTO.setId(users.getId());
            loginResponseDTO.setFullName(users.getFullname());
            loginResponseDTO.setUsername(users.getUsername());
            loginResponseDTO.setRole(users.getRole());
            loginResponseDTO.setToken(tokenHandler.generateToken(users));
            return loginResponseDTO;
        } catch (Exception e) {
            if (users != null && users.getStatus() == AccountStatus.IN_ACTIVATED) {
                throw new BadCredentialsException("Tài khoản chưa được kích hoạt");
            }
            e.printStackTrace();
            throw new BadCredentialsException("Tên đăng nhập hoặc mật khẩu không hợp lệ");
        }
    }

    public void activeAccount(String token) {
        try {
            String username = tokenHandler.getInfoByToken(token);
            // => hopwj le
            Users user = usersRepository.findByUsername(username).get();
            user.setStatus(AccountStatus.ACTIVATED);
            usersRepository.save(user);
        } catch (Exception e) {
            throw new InValidToken("token không hợp lệ");
        }

    }
}