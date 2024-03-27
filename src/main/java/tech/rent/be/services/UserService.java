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

    public List<UserDTO> getAllUsers() {
        List<Users> usersList = usersRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (Users user : usersList) {
            UserDTO userDTO = new UserDTO();
            // Map fields from user to userDTO
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setRole(user.getRole());
            userDTO.setFullname(user.getFullname());
            userDTO.setDateOfBirth(user.getDateOfBirth());
            userDTO.setGender(user.getGender());
            userDTO.setPhoneNumber(user.getPhoneNumber());
            userDTO.setAddress(user.getAddress());
            userDTOList.add(userDTO);

        }
        return userDTOList;
    }

    public UserDTO updateUserData(UserDTO userDTO) {
        Users currentUser = accountUtils.getCurrentUser();

        // Map updated fields from userDTO to currentUser
        currentUser.setEmail(userDTO.getEmail());
        currentUser.setFullname(userDTO.getFullname());
        currentUser.setDateOfBirth(userDTO.getDateOfBirth()); // Ensure proper date format
        currentUser.setGender(userDTO.getGender());
        currentUser.setPhoneNumber(userDTO.getPhoneNumber());
        currentUser.setAddress(userDTO.getAddress());

        Users updatedUser = usersRepository.save(currentUser);
        return convertToDto(updatedUser);
    }

    public UserDTO getUserData() {
        Users user = accountUtils.getCurrentUser();
        UserDTO userDTO = new UserDTO();
        // Map fields from user to userDTO
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole()); // Assuming Role is an Enum or similar
        userDTO.setFullname(user.getFullname());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setGender(user.getGender());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setAddress(user.getAddress());
        return userDTO;
    }

    private UserDTO convertToDto(Users user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole()); // Assuming Role exists and is mapped
        dto.setFullname(user.getFullname());
        dto.setDateOfBirth(user.getDateOfBirth()); // Ensure proper date formatting
        dto.setGender(user.getGender());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setAddress(user.getAddress());
        return dto;
    }

    public UserDTO deleteUseById(Long id) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            usersRepository.delete(user);
            return convertToDto(user);
        } else {

            // Handle case when user with given id is not found
            throw new RuntimeException("User not found with id: " + id);
        }
    }
}
