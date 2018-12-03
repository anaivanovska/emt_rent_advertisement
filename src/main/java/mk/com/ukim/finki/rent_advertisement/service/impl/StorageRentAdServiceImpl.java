package mk.com.ukim.finki.rent_advertisement.service.impl;

import mk.com.ukim.finki.rent_advertisement.domain.dto.ImageDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdDTO;
import mk.com.ukim.finki.rent_advertisement.domain.exceptions.StorageRentAdException;
import mk.com.ukim.finki.rent_advertisement.domain.model.*;
import mk.com.ukim.finki.rent_advertisement.persistence.ImageRepository;
import mk.com.ukim.finki.rent_advertisement.persistence.LocationRepository;
import mk.com.ukim.finki.rent_advertisement.persistence.StorageRentAdRepository;
import mk.com.ukim.finki.rent_advertisement.persistence.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mk.com.ukim.finki.rent_advertisement.service.StorageRentAdService;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public List<StorageRentAdDTO> findStorageRentAds(String username) {
        List<StorageRentAd> storageRentAds = storageRentAdRepository.findAllByUser_Username(username);
        List<StorageRentAdDTO> storageRentAdDTOS = storageRentAds.stream().map(ad -> modelMapper.map(ad, StorageRentAdDTO.class))
                                                                         .collect(Collectors.toList());
        return storageRentAdDTOS;
    }

    @Transactional
    @Override
    public StorageRentAdDTO createStorageRentAd(StorageRentAdDTO storageRentAdDTO, String username) {
        StorageRentAd storageRentAd = modelMapper.map(storageRentAdDTO, StorageRentAd.class);
        User user = userRepository.findById(username).orElse(null);
        Location storageLocation = storageRentAd.getStorageLocation() != null ? findStorageLocation(storageRentAd.getStorageLocation()) : null;
        storageRentAd.setUser(user);
        storageRentAd.setStorageLocation(storageLocation);
        storageRentAd.setStatus(AdvertisementStatus.Open);
        storageRentAd.setCreationDate(LocalDateTime.now());
        storageRentAd = storageRentAdRepository.save(storageRentAd);
        StorageRentAdDTO result = modelMapper.map(storageRentAd, StorageRentAdDTO.class);
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
    public StorageRentAdDTO updateStorageRentAd(StorageRentAdDTO storageRentAdDTO) {
        StorageRentAd storageRentAd = this.findStorageRentAdById(storageRentAdDTO.getId());
        storageRentAd.setTitle(storageRentAdDTO.getTitle());
        storageRentAd.setDescription(storageRentAdDTO.getDescription());
        storageRentAd.setStatus(storageRentAdDTO.getStatus());
        Location storageLocation = modelMapper.map(storageRentAdDTO.getStorageLocation(), Location.class);
        if(storageLocation != null){
            storageLocation = this.findStorageLocation(storageLocation);
        }
        storageRentAd.setStorageLocation(storageLocation);
        storageRentAd = storageRentAdRepository.save(storageRentAd);
        logger.log(Level.INFO, "Storage rent Ad saved in repository, this is its new title "
                + storageRentAd.getTitle());
        StorageRentAdDTO result = modelMapper.map(storageRentAd, StorageRentAdDTO.class);
        result.setImages(findAllImages(result.getId()));
        return result;
    }

    @Transactional
    @Override
    public StorageRentAdDTO resetStorageRentAd_Date(long id) {
        StorageRentAd storageRentAd = this.findStorageRentAdById(id);
        storageRentAd.setCreationDate(LocalDateTime.now());
        storageRentAd = storageRentAdRepository.save(storageRentAd);
        StorageRentAdDTO result = modelMapper.map(storageRentAd, StorageRentAdDTO.class);
        result.setImages(findAllImages(result.getId()));
        return result;
    }

    @Transactional
    @Override
    public long deleteStorageRentAd(long id) {
        storageRentAdRepository.deleteById(id);
        return id;
    }

    private List<ImageDTO> findAllImages(long rentAd_ID){
        List<Image> images = imageRepository.findAllByStorageRentAd_Id(rentAd_ID);
        List<ImageDTO> imageDTOs = images.stream()
                                        .map(image -> modelMapper.map(image, ImageDTO.class))
                                        .collect(Collectors.toList());
        return imageDTOs;
    }
}
