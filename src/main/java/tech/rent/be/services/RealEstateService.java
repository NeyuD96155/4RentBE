package tech.rent.be.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.dto.ResourceDTO;
import tech.rent.be.entity.*;
//import tech.rent.be.entity.Resource;
import tech.rent.be.enums.EstateStatus;
import tech.rent.be.enums.PostStatus;
import tech.rent.be.repository.*;
import tech.rent.be.utils.AccountUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RealEstateService {
    @Autowired
    RealEstateRepository realEstateRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    AccountUtils accountUtils;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    BookingRepository bookingRepository;

    @PersistenceContext
    EntityManager entityManager;

    public RealEstate finRealEstateById(long id) {
        try {
            return realEstateRepository.findRealEstateById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public RealEstate createEstate(RealEstateDTO realEstateDTO) {
        Users users = accountUtils.getCurrentUser();
        Category category = categoryRepository.findCategoryById(realEstateDTO.getCategoryId());
        Location location = locationRepository.findLocationById(realEstateDTO.getLocationId());
        RealEstate realEstate = new RealEstate();

        realEstate.setEstateStatus(EstateStatus.PENDING);

        realEstate.setDescription(realEstateDTO.getDescription());

        realEstate.setTitle(realEstateDTO.getTitle());

        realEstate.setDescription(realEstateDTO.getDescription());
        // realEstate.setDate(realEstateDTO.getDate());
        realEstate.setAmount(realEstateDTO.getAmount());

        realEstate.setPrice(realEstateDTO.getPrice());

        realEstate.setCheckIn(realEstateDTO.getCheckIn());
        realEstate.setCheckOut(realEstateDTO.getCheckOut());

        realEstate.setCategory(category);
        realEstate.setLocation(location);
        realEstate.setUsers(users);
    }

}