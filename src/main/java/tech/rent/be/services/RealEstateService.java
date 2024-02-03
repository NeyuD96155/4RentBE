package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Users;
import tech.rent.be.repository.RealEstateRepository;
import tech.rent.be.repository.UsersRepository;

@Service
public class RealEstateService {
    @Autowired
    RealEstateRepository realEstateRepository;

    @Autowired
    UsersRepository usersRepository;
    public RealEstate createEstate(RealEstateDTO realEstateDTO){
        Users users = usersRepository.findUsersById(realEstateDTO.getUserId());
        RealEstate realEstate = new RealEstate();
        realEstate.setDescription(realEstateDTO.getDescription());
        realEstate.setLocation(realEstateDTO.getLocation());
        realEstate.setType(realEstateDTO.getType());
        realEstate.setName(realEstateDTO.getName());
        realEstate.setUsers(users);
        return realEstateRepository.save(realEstate);
    }
}
