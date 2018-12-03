package mk.com.ukim.finki.rent_advertisement.service;

import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdDTO;

import java.util.List;

public interface StorageRentAdService {
    List<StorageRentAdDTO> findStorageRentAds(String username);
    StorageRentAdDTO createStorageRentAd(StorageRentAdDTO storageRentAdDTO, String username);
    StorageRentAdDTO updateStorageRentAd(StorageRentAdDTO storageRentAdDTO);
    StorageRentAdDTO resetStorageRentAd_Date(long id);
    long deleteStorageRentAd(long id);
}
