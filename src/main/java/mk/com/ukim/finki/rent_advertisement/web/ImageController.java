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

@RestController
@RequestMapping(value = "/api/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ImageController {

    @Autowired
    private ImageServiceImpl imageService;

    @PostMapping("/addAll/{rentAd_ID}")
    public List<ImageDTO> saveImages(@PathVariable long rentAd_ID, @RequestParam("images") MultipartFile[] images) throws IOException {
        if(images.length == 0){
            return null;
        }
        List<ImageDTO> imageDTOs = new ArrayList<>();
        for (MultipartFile image : images) {
            String imgName = image.getOriginalFilename();
            byte[] img = image.getBytes();
            System.out.println("Image: " + imgName);

            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setTitle(imgName);
            imageDTO.setImage(img);
            imageDTOs.add(imageDTO);
        }

        return imageService.saveImages(imageDTOs, rentAd_ID);
    }

    @PostMapping("/add/{rentAd_ID}")
    public ImageDTO saveImage(@PathVariable long rentAd_ID, @RequestParam("image") MultipartFile image) throws IOException {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setTitle(image.getOriginalFilename());
        imageDTO.setImage(image.getBytes());
        return imageService.saveImage(imageDTO, rentAd_ID);
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
