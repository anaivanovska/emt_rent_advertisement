package mk.com.ukim.finki.rent_advertisement.web;

import mk.com.ukim.finki.rent_advertisement.domain.dto.ImageDTO;
import mk.com.ukim.finki.rent_advertisement.domain.exceptions.StorageRentAdException;
import mk.com.ukim.finki.rent_advertisement.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/image")
public class ImageController {

    @Autowired
    private ImageServiceImpl imageService;

    @PostMapping("/addAll/{rentAd_ID}")
    public List<ImageDTO> saveImages(@PathVariable long rentAd_ID, @RequestBody List<ImageDTO> images) throws IOException {
        return imageService.saveImages(images, rentAd_ID);
    }

    @PostMapping(value = "/add/{rentAd_ID}")
    public ImageDTO saveImage(@PathVariable long rentAd_ID, @RequestBody ImageDTO image) throws IOException {
        return imageService.saveImage(image, rentAd_ID);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable long id){
        imageService.deleteImage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all/{rentAd_ID}")
    public List<ImageDTO> findAllImages(@PathVariable long rentAd_ID){
        return imageService.findAllImages(rentAd_ID);
    }
}
