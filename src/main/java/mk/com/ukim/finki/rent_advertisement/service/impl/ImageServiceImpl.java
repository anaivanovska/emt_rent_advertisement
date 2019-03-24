package mk.com.ukim.finki.rent_advertisement.service.impl;

import mk.com.ukim.finki.rent_advertisement.domain.dto.ImageDTO;
import mk.com.ukim.finki.rent_advertisement.domain.exceptions.StorageRentAdException;
import mk.com.ukim.finki.rent_advertisement.domain.model.Image;
import mk.com.ukim.finki.rent_advertisement.domain.model.StorageRentAd;
import mk.com.ukim.finki.rent_advertisement.persistence.ImageRepository;
import mk.com.ukim.finki.rent_advertisement.persistence.StorageRentAdRepository;
import mk.com.ukim.finki.rent_advertisement.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StorageRentAdRepository storageRentAdRepository;

    private StorageRentAd findStorageRentAdById(long id){
        return storageRentAdRepository.findById(id).orElseThrow(() -> new StorageRentAdException("Storage Rent Ad with id " + id + " not found"));
    }

    @Override
    public List<ImageDTO> saveImages(List<ImageDTO> imageDTOs, long rentAd_ID) {
        StorageRentAd storageRentAd = findStorageRentAdById(rentAd_ID);
        Iterable<Image> images = imageDTOs.stream()
                                         .map(imageDTO -> {
                                             Image image = modelMapper.map(imageDTO, Image.class);
                                             image.setStorageRentAd(storageRentAd);
                                             return image;
                                         })
                                         .collect(Collectors.toList());
        images = imageRepository.saveAll(images);
        List<ImageDTO> result = findAllImages(rentAd_ID);
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImageDTO> findAllImages(long rentAd_ID){
        List<Image> images = imageRepository.findAllByStorageRentAd_Id(rentAd_ID);
        List<ImageDTO> imageDTOs = images.stream()
                .map(image -> modelMapper.map(image, ImageDTO.class))
                .collect(Collectors.toList());
        if(imageDTOs.size() == 0){
            throw  new StorageRentAdException("No images found");
        }
        return imageDTOs;
    }

    @Override
    public long deleteImage(long id) {
        imageRepository.deleteById(id);
        return id;
    }

    @Override
    public ImageDTO saveImage(ImageDTO imageDTO, long rentAd_ID) {
        StorageRentAd storageRentAd = findStorageRentAdById(rentAd_ID);
        Image image = modelMapper.map(imageDTO, Image.class);
        image.setStorageRentAd(storageRentAd);
        image = imageRepository.save(image);
        ImageDTO result = modelMapper.map(image, ImageDTO.class);
        return result;
    }
}

