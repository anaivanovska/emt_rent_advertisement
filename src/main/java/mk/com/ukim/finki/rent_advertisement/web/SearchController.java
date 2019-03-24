package mk.com.ukim.finki.rent_advertisement.web;

import mk.com.ukim.finki.rent_advertisement.domain.Constants.Constants;
import mk.com.ukim.finki.rent_advertisement.domain.dto.StorageRentAdShortDTO;
import mk.com.ukim.finki.rent_advertisement.service.impl.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/search")
public class SearchController {
    @Autowired
    private SearchServiceImpl searchService;


    @GetMapping("/{username}/{shouldSearchByUsername}")
    public Page<StorageRentAdShortDTO> getAllStorageRentAds(@RequestParam(required = true) String keyword,
                                                            @PathVariable(name = "username") String username,
                                                            @PathVariable(name = "shouldSearchByUsername") Boolean shouldSearchByUsername,
                                                            @PageableDefault(page = Constants.DEFAULT_PAGE_NUMBER, size = Constants.DEFAULT_PAGE_SIZE)
                                                            @SortDefault.SortDefaults({@SortDefault(sort = "creationDate", direction = Sort.Direction.DESC)}) Pageable pageable) {

        return searchService.searchByTitle(keyword, username, shouldSearchByUsername, pageable);
    }
}
