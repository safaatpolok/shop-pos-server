package com.shop.service;

import com.shop.domain.StoreStatus;
import com.shop.exceptions.UserException;
import com.shop.modal.Store;
import com.shop.modal.User;
import com.shop.payload.dto.StoreDTO;

import java.util.List;

public interface StoreService {

   StoreDTO createStore(StoreDTO storeDTO, User user);
      StoreDTO getStore(Long id) throws Exception;
      List<StoreDTO> getAllStores();
      Store getStoreByAdmin() throws UserException;
      StoreDTO updateStore(Long id, StoreDTO storeDTO) throws Exception;
      void deleteStore(Long id) throws UserException;
      StoreDTO getStoreByEmployee() throws UserException;

      StoreDTO modrateStore(Long id, StoreStatus status) throws Exception;
}
