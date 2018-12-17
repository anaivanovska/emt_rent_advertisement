package mk.com.ukim.finki.rent_advertisement.service.impl;

import mk.com.ukim.finki.rent_advertisement.domain.dto.ImageDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdFullDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdShortDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.exceptions.StorageRentAdException;
import mk.com.ukim.finki.rent_advertisement.domain.model.*;
import mk.com.ukim.finki.rent_advertisement.persistence.ImageRepository;
import mk.com.ukim.finki.rent_advertisement.persistence.LocationRepository;
import mk.com.ukim.finki.rent_advertisement.persistence.StorageRentAdRepository;
import mk.com.ukim.finki.rent_advertisement.persistence.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import mk.com.ukim.finki.rent_advertisement.service.StorageRentAdService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
public class StorageRentAdServiceImpl implements StorageRentAdService {
    private Logger logger = Logger.getLogger(StorageRentAdServiceImpl.class.getName());
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StorageRentAdRepository storageRentAdRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private LocationRepository locationRepository;


    @Transactional(readOnly = true)
    @Override
    public Page<StorageRentAdShortDTO> findStorageRentAds(String username, Pageable pageable) {
        Page<StorageRentAd> storageRentAds = storageRentAdRepository.findAllByPublisher_Username(username, pageable);
        Page<StorageRentAdShortDTO> storageRentAdDTOS = storageRentAds.map(ad -> {
            System.out.println("Size: " + ad.getImages().size());
            return modelMapper.map(ad, StorageRentAdShortDTO.class);
        });
        return storageRentAdDTOS;
    }

    @Transactional
    @Override
    public StorageRentAdShortDTO createStorageRentAd(StorageRentAdShortDTO storageRentAdShortDTO, String username) {
        StorageRentAd storageRentAd = modelMapper.map(storageRentAdShortDTO, StorageRentAd.class);
        User user = userRepository.findById(username).orElse(null);
        Location storageLocation = storageRentAd.getStorageLocation() != null ? findStorageLocation(storageRentAd.getStorageLocation()) : null;
        storageRentAd.setPublisher(user);
        storageRentAd.setStorageLocation(storageLocation);
        storageRentAd.setStatus(AdvertisementStatus.Open);
        storageRentAd.setCreationDate(LocalDateTime.now());
        storageRentAd = storageRentAdRepository.save(storageRentAd);
        StorageRentAdShortDTO result = modelMapper.map(storageRentAd, StorageRentAdShortDTO.class);
        return result;
    }

    private Location findStorageLocation(Location sentStorageLocation) {
        Location savedStorageLocation = locationRepository.findLocationByLatitudeAndLongitude(sentStorageLocation.getLatitude(), sentStorageLocation.getLongitude());
        if(savedStorageLocation != null){
            return savedStorageLocation;
        } else {
            return locationRepository.save(sentStorageLocation);
        }
    }
    private StorageRentAd findStorageRentAdById(long id){
        StorageRentAd storageRentAd = storageRentAdRepository.findById(id)
                                                              .orElseThrow(() -> new StorageRentAdException("Storage rent ad with id: " + id + " not found"));
        return storageRentAd;
    }
    @Transactional
    @Override
    public StorageRentAdShortDTO updateStorageRentAd(StorageRentAdShortDTO storageRentAdShortDTO) {
        StorageRentAd storageRentAd = this.findStorageRentAdById(storageRentAdShortDTO.getId());
        storageRentAd.setTitle(storageRentAdShortDTO.getTitle());
        storageRentAd.setDescription(storageRentAdShortDTO.getDescription());
        storageRentAd.setStatus(storageRentAdShortDTO.getStatus());
        Location storageLocation = modelMapper.map(storageRentAdShortDTO.getStorageLocation(), Location.class);
        if(storageLocation != null){
            storageLocation = this.findStorageLocation(storageLocation);
        }
        storageRentAd.setStorageLocation(storageLocation);
        storageRentAd = storageRentAdRepository.save(storageRentAd);
        logger.log(Level.INFO, "Storage rent Ad saved in repository, this is its new title "
                + storageRentAd.getTitle());
        StorageRentAdShortDTO result = modelMapper.map(storageRentAd, StorageRentAdShortDTO.class);
        result.setImages(findAllImages(result.getId()));
        return result;
    }

    @Transactional
    @Override
    public StorageRentAdShortDTO resetStorageRentAd_Date(long id) {
        StorageRentAd storageRentAd = this.findStorageRentAdById(id);
        storageRentAd.setCreationDate(LocalDateTime.now());
        storageRentAd = storageRentAdRepository.save(storageRentAd);
        StorageRentAdShortDTO result = modelMapper.map(storageRentAd, StorageRentAdShortDTO.class);
        result.setImages(findAllImages(result.getId()));
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public StorageRentAdShortDTO getShortStorageRentAd(long id) {
        StorageRentAd storageRentAd = this.findStorageRentAdById(id);
        StorageRentAdShortDTO storageRentAdShortDTO = modelMapper.map(storageRentAd, StorageRentAdShortDTO.class);
        return storageRentAdShortDTO;
    }

    @Override
    public StorageRentAdFullDTO getFullStorageRentAd(long id) {
        StorageRentAd storageRentAd = this.findStorageRentAdById(id);
        StorageRentAdFullDTO storageRentAdFullDTO = modelMapper.map(storageRentAd, StorageRentAdFullDTO.class);
        return storageRentAdFullDTO;
    }

    @Transactional
    @Override
    public long deleteStorageRentAd(long id) {
        storageRentAdRepository.deleteById(id);
        return id;
    }

    private List<ImageDTO> findAllImages(long rentAd_ID){
        List<Image> images = imageRepository.findAllByStorageRentAd_Id(rentAd_ID);
        System.out.println("Find all images: " + images.size());
        List<ImageDTO> imageDTOs = images.stream()
                                         .map(image -> modelMapper.map(image, ImageDTO.class))
                                         .collect(Collectors.toList());
        return imageDTOs;
    }
}
