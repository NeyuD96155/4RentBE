package tech.rent.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.services.RealEstateService;

@RestController
@CrossOrigin
public class RealEstateController {
    @Autowired
    RealEstateService realEstateService;

    @PostMapping("/estate")
    public ResponseEntity estate(@RequestBody RealEstateDTO realEstateDTO){
        RealEstate realEstate = realEstateService.createEstate(realEstateDTO);
        return ResponseEntity.ok(realEstate);
    }

}
