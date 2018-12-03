package mk.com.ukim.finki.rent_advertisement.web;

import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdDTO;
import mk.com.ukim.finki.rent_advertisement.service.impl.StorageRentAdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/storageRentAd")
public class StorageRentAdController {
    @Autowired
    private StorageRentAdServiceImpl storageRentAdService;

    @GetMapping("/all/{username}")
    public Set<StorageRentAdDTO> getAllStorageRentAds(@PathVariable(name = "username") String username){
        return storageRentAdService.findStorageRentAds(username);
    }

    @PostMapping("/create/{username}")
    public StorageRentAdDTO createNewStorageRentAd(@RequestBody StorageRentAdDTO storageRentAdDTO, @PathVariable(name = "username") String username) {
        return storageRentAdService.createStorageRentAd(storageRentAdDTO, username);
    }

    @PutMapping("/update")
    public StorageRentAdDTO updateStorageRentAd(@RequestBody StorageRentAdDTO storageRentAdDTO){
        return storageRentAdService.updateStorageRentAd(storageRentAdDTO);
    }

    @PutMapping("/resetDate/{id}")
    public StorageRentAdDTO resetStorageRentAdDate(@PathVariable long id){
        return storageRentAdService.resetStorageRentAd_Date(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStorageRentAd(@PathVariable(name = "id") long id){
        storageRentAdService.deleteStorageRentAd(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
