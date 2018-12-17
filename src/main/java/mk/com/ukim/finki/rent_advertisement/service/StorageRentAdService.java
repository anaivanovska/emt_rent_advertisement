package mk.com.ukim.finki.rent_advertisement.service;

import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdFullDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdShortDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StorageRentAdService {
    Page<StorageRentAdShortDTO> findStorageRentAds(String username, Pageable pageable);
    StorageRentAdShortDTO createStorageRentAd(StorageRentAdShortDTO storageRentAdShortDTO, String username);
    StorageRentAdShortDTO updateStorageRentAd(StorageRentAdShortDTO storageRentAdShortDTO);
    StorageRentAdShortDTO resetStorageRentAd_Date(long id);
    StorageRentAdShortDTO getShortStorageRentAd(long id);
    StorageRentAdFullDTO  getFullStorageRentAd(long id);
    long deleteStorageRentAd(long id);

}
