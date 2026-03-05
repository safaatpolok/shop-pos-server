package com.shop.controller;

import com.shop.domain.StoreStatus;
import com.shop.exceptions.UserException;
import com.shop.mapper.StoreMapper;
import com.shop.modal.User;
import com.shop.payload.dto.StoreDTO;
import com.shop.payload.response.ApiResponse;
import com.shop.service.StoreService;
import com.shop.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private StoreService storeService;
    private UserService userService;

    @PostMapping

    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO,
                                                @RequestHeader("Authorization") String jwt) throws UserException {
        User user = userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDTO, user));


    }



    @GetMapping()
    public ResponseEntity<List<StoreDTO>> getAllStores(@RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(storeService.getAllStores());
    }
    @GetMapping("/admin")
    public ResponseEntity<StoreDTO> getStoreByAdmin(@RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));
    }


    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(@RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
     @PreAuthorize(" hasAuthority  ('ROLE_STORE_ADMIN')")
    public ResponseEntity<StoreDTO> updateStore(@PathVariable Long id,
                                                @RequestBody StoreDTO storeDTO) throws Exception {


        return ResponseEntity.ok(new StoreDTO());
    }

    @PutMapping("/{id}/moderate")

    public ResponseEntity<StoreDTO> moderateStore(@PathVariable Long id,
                                                @RequestParam StoreStatus status
                                                ) throws Exception {


        return ResponseEntity.ok(storeService.modrateStore(id, status));
    }


    @GetMapping("/{id}")

    public ResponseEntity<StoreDTO> getStoreById(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {


        return ResponseEntity.ok(storeService.getStore(id));
    }

    @DeleteMapping ("/{id}")

    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id
                                                   ) throws Exception {
        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Store deleted successfully");



        return ResponseEntity.ok(apiResponse);
    }

}
