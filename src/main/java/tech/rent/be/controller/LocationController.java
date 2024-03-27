package tech.rent.be.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.rent.be.dto.CategoryDTO;
import tech.rent.be.dto.LocationDTO;
import tech.rent.be.entity.Category;
import tech.rent.be.entity.Location;
import tech.rent.be.services.CategoryService;
import tech.rent.be.services.LocationService;

import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class LocationController {
    @Autowired
    LocationService locationService;
    @PostMapping("/location")
    public ResponseEntity location(@RequestBody LocationDTO locationDTO){
        Location location = locationService.CreateLocation(locationDTO);
        return  ResponseEntity.ok(location);
    }

    @GetMapping("/showLocation")
    public ResponseEntity<List<LocationDTO>> getAllLocation() {
        List<LocationDTO> locations = locationService.getAllLocation();
        return ResponseEntity.ok(locations);
    }

}
