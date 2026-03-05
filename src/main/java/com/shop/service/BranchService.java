package com.shop.service;

import com.shop.exceptions.UserException;
import com.shop.modal.User;
import com.shop.payload.dto.BranchDTO;

import java.util.List;

public interface BranchService {


    BranchDTO createBranch(BranchDTO branchDTO) throws UserException;
    BranchDTO updateBranch(Long id,BranchDTO branchDTO) throws Exception;
    void deleteBranch(Long id) throws Exception;

    List<BranchDTO> getAllBranchesByStoreId(Long storeId);

    BranchDTO getBranchById(Long id) throws Exception;



}
