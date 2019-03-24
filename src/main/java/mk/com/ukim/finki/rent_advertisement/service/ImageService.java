package mk.com.ukim.finki.rent_advertisement.service;

import mk.com.ukim.finki.rent_advertisement.domain.dto.ImageDTO;

import java.util.List;

public interface ImageService {
    List<ImageDTO> saveImages(List<ImageDTO> imageDTOs, long rentAd_ID);
    long deleteImage(long id);
    ImageDTO saveImage(ImageDTO imageDTO, long rentAd_ID);
    List<ImageDTO> findAllImages(long rentAd_ID);
}
