package tech.rent.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.dto.ResourceDTO;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Resource;
import tech.rent.be.services.RealEstateService;
@RestController
@CrossOrigin("*")
public class ResourceController {
    @Autowired
    RealEstateService realEstateService;

    @PostMapping("/resource")
    public ResponseEntity resource(@RequestBody RealEstateDTO realEstate){

        RealEstate resource = realEstateService.createEstate(realEstate);
        return ResponseEntity.ok(resource);

    }
}
