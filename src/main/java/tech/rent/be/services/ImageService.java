//package tech.rent.be.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import tech.rent.be.dto.ImageDTO;
//import tech.rent.be.entity.Image;
//import tech.rent.be.entity.Post;
//import tech.rent.be.entity.RealEstate;
//import tech.rent.be.repository.ImageRepository;
//import tech.rent.be.repository.PostRepository;
//import tech.rent.be.repository.RealEstateRepository;
//
//import java.io.IOException;
//
//@Service
//public class ImageService {
//    @Autowired
//    ImageRepository imageRepository;
//
//    @Autowired
//    PostRepository postRepository;
//
//    @Autowired
//    RealEstateRepository realEstateRepository;
//
//    public Image uploadImage(ImageDTO imageDTO) {
//        Image image = new Image();
//        image.setImageData(convertMultipartFileToBytes(imageDTO.getImageFile()));
//
//        if (imageDTO.getPostId() != null) {
//            Post post = postRepository.findById(imageDTO.getPostId()).orElse(null);
//            image.setPost(post);
//        } else if (imageDTO.getRealEstateId() != null) {
//            RealEstate realEstate = realEstateRepository.findById(imageDTO.getRealEstateId()).orElse(null);
//            image.setRealEstate(realEstate);
//        }
//
//        return imageRepository.save(image);
//    }
//
//    private byte[] convertMultipartFileToBytes(MultipartFile file) {
//        try {
//            return file.getBytes();
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to convert MultipartFile to bytes: " + e.getMessage());
//        }
//    }
//}
