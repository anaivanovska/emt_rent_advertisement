package mk.com.ukim.finki.rent_advertisement.service.impl;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdShortDTO;
import mk.com.ukim.finki.rent_advertisement.domain.model.StorageRentAd;
import mk.com.ukim.finki.rent_advertisement.persistence.search.SearchRepositoryImpl;
import mk.com.ukim.finki.rent_advertisement.service.SearchService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {

    private final SearchRepositoryImpl searchRepositoryImpl;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public SearchServiceImpl(SearchRepositoryImpl searchRepository) {
        this.searchRepositoryImpl = searchRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<StorageRentAdShortDTO> searchByTitle(String keyword, String username, boolean shouldSearchByUsername, Pageable pageable) {
        List<StorageRentAd> storageRentAds = searchRepositoryImpl.searchPhrase(StorageRentAd.class,keyword,  "title");
        Logger LOG = Logger.getLogger(SearchServiceImpl.class.toString());
        LOG.log(Level.INFO, storageRentAds.size() + " -  list size");
        List<StorageRentAdShortDTO> filteredStorageRentAds= storageRentAds.stream()
                                                                    .filter(storageRentAd ->{
                                                                        String publisherUsername = storageRentAd.getPublisher().getUsername();
                                                                        boolean isStorageRentAdAdequate = (shouldSearchByUsername == publisherUsername.equals(username));
                                                                        return isStorageRentAdAdequate;
                                                                    })
                                                                    .map(storageRentAd -> modelMapper.map(storageRentAd, StorageRentAdShortDTO.class))
                                                                    .collect(Collectors.toList());
        Page<StorageRentAdShortDTO> storageRentAdShortDTOS = new PageImpl<>(filteredStorageRentAds, pageable, filteredStorageRentAds.size());
        LOG.log(Level.INFO, storageRentAds.size() + " -  filtered list size");
        LOG.log(Level.INFO, storageRentAdShortDTOS.getTotalElements() + " - page total Elements");
        return storageRentAdShortDTOS;
    }
}
