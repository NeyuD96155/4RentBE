package tech.rent.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.services.RealEstateService;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class RealEstateController {
    @Autowired
    RealEstateService realEstateService;

    @PostMapping("/estate")
    public ResponseEntity estate(@RequestBody RealEstateDTO realEstateDTO) {
        RealEstate realEstate = realEstateService.createEstate(realEstateDTO);
        return ResponseEntity.ok(realEstate);
    }

    @GetMapping("/showEstate")
    public ResponseEntity<List<RealEstateDTO>> getAllRealEstate() {
        List<RealEstateDTO> estates = realEstateService.getAllRealEstate();
        return ResponseEntity.ok(estates);
    }
    @GetMapping("/showEstateToAdmin")
    public ResponseEntity<List<RealEstateDTO>> getAllRealEstateToAdmin() {
        List<RealEstateDTO> estates = realEstateService.getAllRealEstateToAdmin();
        return ResponseEntity.ok(estates);
    }

    @GetMapping("/showEstateDetail/{id}")
    public ResponseEntity<RealEstateDTO> getEstateById(@PathVariable long id) {
        RealEstateDTO estateById = realEstateService.getEstateById(id);
        return ResponseEntity.ok(estateById);
    }
    @GetMapping("/allRealEstateOfCurrentUser")
    public ResponseEntity<List<RealEstateDTO>> getAllEstateByCurrentUser() {
        try {
            List<RealEstateDTO> realEstateDTO = realEstateService.getAllEstateByCurrentUser();
            return ResponseEntity.ok(realEstateDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        // Or you could use @ExceptionHandler to handle exceptions globally
    }


    @GetMapping("/search")
    public ResponseEntity<List<RealEstateDTO>> estateSearch(@RequestParam(required = false, defaultValue = "0") long categoryId, @RequestParam(required = false, defaultValue = "0") long locationId, @RequestParam(required = false, defaultValue = "0") long amount, @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate from, @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate to) {
        List<RealEstateDTO> estateSearch = realEstateService.search(categoryId, locationId, amount, from, to);
        return ResponseEntity.ok(estateSearch);
    }

    @PutMapping("/authorizeApprove/{estateId}")
    public ResponseEntity<RealEstate> estateAuth(@PathVariable Long estateId){
        RealEstate estate = realEstateService.AuthEstateApprove(estateId);
        return ResponseEntity.ok(estate);
    }
    @PutMapping("/authorizeReject/{estateId}")
    public ResponseEntity<RealEstate> estateAuthReject(@PathVariable Long estateId){
        RealEstate estate = realEstateService.AuthEstateReject(estateId);
        return ResponseEntity.ok(estate);
    }
    @PutMapping("/deletedEstate/{estateId}")
    public ResponseEntity<RealEstate> estateDelete(@PathVariable Long estateId){
        RealEstate estate = realEstateService.EstateDelete(estateId);
        return ResponseEntity.ok(estate);
    }




}