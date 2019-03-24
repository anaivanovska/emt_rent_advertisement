package mk.com.ukim.finki.rent_advertisement.service;

import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdShortDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SearchService {
    Page<StorageRentAdShortDTO> searchByTitle(String keyword, String username, boolean shouldSearchByUsername, Pageable pageable);
}
