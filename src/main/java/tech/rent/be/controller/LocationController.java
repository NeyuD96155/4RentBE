package tech.rent.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.rent.be.dto.CategoryDTO;
import tech.rent.be.dto.LocationDTO;
import tech.rent.be.entity.Category;
import tech.rent.be.entity.Location;

import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class LocationController {
    @Autowired
    LocationService locationService;

}
