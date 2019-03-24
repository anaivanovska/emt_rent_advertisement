package mk.com.ukim.finki.rent_advertisement.web;

import mk.com.ukim.finki.rent_advertisement.domain.Constants.Constants;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdFullDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdShortDTO;
import mk.com.ukim.finki.rent_advertisement.service.impl.StorageRentAdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/storageRentAd")
@CrossOrigin("*")
public class StorageRentAdController {
    @Autowired
    private StorageRentAdServiceImpl storageRentAdService;

    @GetMapping("/all/{username}")
    public Page<StorageRentAdShortDTO> getAllStorageRentAds(@PathVariable(name = "username") String username,
                                                            @PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
           @SortDefault.SortDefaults({@SortDefault(sort = "creationDate", direction = Sort.Direction.DESC)}) Pageable pageable) {
        return storageRentAdService.findStorageRentAds(username, pageable);
    }

    @PostMapping("/create/{username}")
    public StorageRentAdShortDTO createNewStorageRentAd(@RequestBody StorageRentAdShortDTO storageRentAdShortDTO, @PathVariable(name = "username") String username) {
        return storageRentAdService.createStorageRentAd(storageRentAdShortDTO, username);
    }

    @PutMapping("/update")
    public StorageRentAdShortDTO updateStorageRentAd(@RequestBody StorageRentAdShortDTO storageRentAdShortDTO){
        return storageRentAdService.updateStorageRentAd(storageRentAdShortDTO);
    }

    @PutMapping("/resetDate/{id}")
    public StorageRentAdShortDTO resetStorageRentAdDate(@PathVariable long id){
        return storageRentAdService.resetStorageRentAd_Date(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStorageRentAd(@PathVariable(name = "id") long id){
        storageRentAdService.deleteStorageRentAd(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/short/{id}")
    public StorageRentAdShortDTO getShortStorageRentAd(@PathVariable long id){
        return storageRentAdService.getShortStorageRentAd(id);
    }

    @GetMapping("/full/{id}")
    public StorageRentAdFullDTO getFullStorageRentAd(@PathVariable long id){
        return storageRentAdService.getFullStorageRentAd(id);
    }
}
