//package tech.rent.be.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import tech.rent.be.dto.ImageDTO;
//import tech.rent.be.entity.Image;
//import tech.rent.be.services.ImageService;
//
//@RestController
//@CrossOrigin
//public class ImageController {
//    @Autowired
//    ImageService imageService;
//
//    @PostMapping("/image")
//    public ResponseEntity uploadImageForPost(
//            @PathVariable Long postId,
//            @RequestParam("imageFile") MultipartFile imageFile
//    ) {
//        ImageDTO imageDTO = new ImageDTO(postId, null, imageFile);
//        Image image = imageService.uploadImage(imageDTO);
//        return ResponseEntity.ok(image);
//    }
//
//    @PostMapping("/upload/realEstate/{realEstateId}")
//    public ResponseEntity uploadImageForRealEstate(
//            @PathVariable Long realEstateId,
//            @RequestParam("imageFile") MultipartFile imageFile
//    ) {
//        ImageDTO imageDTO = new ImageDTO(null, realEstateId, imageFile);
//        Image image = imageService.uploadImage(imageDTO);
//        return ResponseEntity.ok(image);
//    }
//}
