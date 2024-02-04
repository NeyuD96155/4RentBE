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
        List<RealEstate> realEstates = category.getEstates();
        if (realEstates == null) {
            realEstates = new ArrayList<>();
        }
        realEstates.add(realEstate);
        category.setEstates(realEstates);
        List<RealEstate> realEstate02 = category.getEstates();
        if (realEstate02 == null) {
            realEstate02 = new ArrayList<>();
        }
        realEstate02.add(realEstate);
        category.setEstates(realEstate02);
        List<Resource> resources = new ArrayList<>();
        // ResourceDTO => Resource
        for (ResourceDTO resourceDTO : realEstateDTO.getResources()) {
            Resource resource = new Resource();
            resource.setResourceType(resourceDTO.getResourceType());
            resource.setUrl(resourceDTO.getUrl());
            resource.setRealEstate(realEstate);
            resources.add(resource);
        }
        realEstate.setResource(resources);

        return realEstateRepository.save(realEstate);
    }

    public List<RealEstateDTO> getAllRealEstate() {
        List<RealEstate> estateList = realEstateRepository.findRealEstatesByEstateStatus(EstateStatus.APPROVED);
        return convertToDTO(estateList);
    }

    public List<RealEstateDTO> convertToDTO(List<RealEstate> estateList) {
        List<RealEstateDTO> estateDTOList = new ArrayList<>();

        for (RealEstate realEstate : estateList) {
            RealEstateDTO realEstateDTO = new RealEstateDTO();
            // Map
            realEstateDTO.setId(realEstate.getId());
            realEstateDTO.setTitle(realEstate.getTitle());
            realEstateDTO.setDescription(realEstate.getDescription());
            // realEstateDTO.setDate(realEstate.getDate());
            realEstateDTO.setEstateStatus(realEstate.getEstateStatus());
            realEstateDTO.setAmount(realEstate.getAmount());
            realEstateDTO.setCheckIn(realEstate.getCheckIn());
            realEstateDTO.setCheckOut(realEstate.getCheckOut());
            realEstateDTO.setPrice(realEstate.getPrice());
            System.out.println(realEstate.getLocation().getLocation());
            System.out.println(realEstate.getCategory().getCategoryname());
            realEstateDTO.setLocation(realEstate.getLocation().getLocation());
            realEstateDTO.setCategory(realEstate.getCategory().getCategoryname());
            realEstateDTO.setCategoryId(realEstate.getCategory().getId());
            realEstateDTO.setLocationId(realEstate.getLocation().getId());

            estateDTOList.add(realEstateDTO);

            List<ResourceDTO> resourceDTOS = new ArrayList<>();
            // ResourceDTO => Resource
            for (Resource resource : realEstate.getResource()) {
                ResourceDTO resourceDTO = new ResourceDTO();
                // resourceDTO.setId(realEstate.getId());
                resourceDTO.setResourceType(resource.getResourceType());
                resourceDTO.setUrl(resource.getUrl());
                resourceDTOS.add(resourceDTO);
            }
            realEstateDTO.setResources(resourceDTOS);

        }
        return estateDTOList;
    }

    public List<RealEstateDTO> getAllRealEstateToAdmin() {
        List<RealEstate> estateListA = realEstateRepository.findAll();
        return convertToDTOtoAdmin(estateListA);
    }

    public List<RealEstateDTO> convertToDTOtoAdmin(List<RealEstate> estateList) {
        List<RealEstateDTO> estateDTOList = new ArrayList<>();

        for (RealEstate realEstate : estateList) {
            RealEstateDTO realEstateDTO = new RealEstateDTO();
            // Map
            realEstateDTO.setId(realEstate.getId());
            realEstateDTO.setTitle(realEstate.getTitle());
            realEstateDTO.setDescription(realEstate.getDescription());
            // realEstateDTO.setDate(realEstate.getDate());
            realEstateDTO.setEstateStatus(realEstate.getEstateStatus());
            realEstateDTO.setAmount(realEstate.getAmount());
            realEstateDTO.setCheckIn(realEstate.getCheckIn());
            realEstateDTO.setCheckOut(realEstate.getCheckOut());
            realEstateDTO.setPrice(realEstate.getPrice());
            System.out.println(realEstate.getLocation().getLocation());
            System.out.println(realEstate.getCategory().getCategoryname());
            realEstateDTO.setLocation(realEstate.getLocation().getLocation());
            realEstateDTO.setCategory(realEstate.getCategory().getCategoryname());
            realEstateDTO.setCategoryId(realEstate.getCategory().getId());
            realEstateDTO.setLocationId(realEstate.getLocation().getId());

            estateDTOList.add(realEstateDTO);

            List<ResourceDTO> resourceDTOS = new ArrayList<>();
            // ResourceDTO => Resource
            for (Resource resource : realEstate.getResource()) {
                ResourceDTO resourceDTO = new ResourceDTO();
                // resourceDTO.setId(realEstate.getId());
                resourceDTO.setResourceType(resource.getResourceType());
                resourceDTO.setUrl(resource.getUrl());
                resourceDTOS.add(resourceDTO);
            }
            realEstateDTO.setResources(resourceDTOS);

        }
        return estateDTOList;
    }

    public List<RealEstateDTO> getAllEstateByCurrentUser() {
        Users currentUser = accountUtils.getCurrentUser();
        if (currentUser == null) {
            // Handle the case where the user is not foundq
            return null;
        }

        List<RealEstate> userRealEstates = realEstateRepository.findRealEstatesByUsers(currentUser);
        if (userRealEstates.isEmpty()) {
            // Handle the case where the user has not posted any real estate properties
            return null;
        }

        List<RealEstateDTO> realEstateDTOList = new ArrayList<>();
        for (RealEstate realEstate : userRealEstates) {
            RealEstateDTO realEstateDTO = new RealEstateDTO();
            // Map real estate entity to DTO
            realEstateDTO.setId(realEstate.getId());
            realEstateDTO.setTitle(realEstate.getTitle());
            realEstateDTO.setDescription(realEstate.getDescription());
            realEstateDTO.setAmount(realEstate.getAmount());
            realEstateDTO.setPrice(realEstate.getPrice());
            realEstateDTO.setCategory(realEstate.getCategory().getCategoryname());
            realEstateDTO.setCategoryId(realEstate.getCategory().getId());
            realEstateDTO.setLocation(realEstate.getLocation().getLocation());
            realEstateDTO.setLocationId(realEstate.getLocation().getId());
            realEstateDTO.setCheckIn(realEstate.getCheckIn());
            realEstateDTO.setCheckOut(realEstate.getCheckOut());

            List<ResourceDTO> resourceDTOList = new ArrayList<>();

            for (Resource resource : realEstate.getResource()) {
                // Map resource entity to DTO
                ResourceDTO resourceDTO = new ResourceDTO();
                resourceDTO.setResourceType(resource.getResourceType());
                resourceDTO.setUrl(resource.getUrl());
                resourceDTOList.add(resourceDTO);
            }
            realEstateDTO.setResources(resourceDTOList);

            realEstateDTOList.add(realEstateDTO);
        }

        return realEstateDTOList;
    }

}