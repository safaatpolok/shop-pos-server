package com.shop.service.impl;

import com.shop.Repositorty.BranchRepository;
import com.shop.Repositorty.StoreRepository;
import com.shop.Repositorty.UserRepository;
import com.shop.exceptions.UserException;
import com.shop.mapper.BranchMapper;
import com.shop.modal.Branch;
import com.shop.modal.Store;
import com.shop.modal.User;
import com.shop.payload.dto.BranchDTO;
import com.shop.service.BranchService;
import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public BranchDTO createBranch(BranchDTO branchDTO) throws UserException {
        User currentUser=userService.getCurrentUser();
        Store store =storeRepository.findByStoreAdminId(currentUser.getId());

//        if (store == null) {
//            throw new UserException("Store not found for user: " + currentUser.getEmail() +
//                    ". You must create a store before adding a branch.");
//        }

        Branch branch=BranchMapper.toEntity(branchDTO,store);
        Branch savedBranch=branchRepository.save(branch);

        return BranchMapper.toDTO(savedBranch);
    }



    @Override
    public BranchDTO updateBranch(Long id,BranchDTO branchDTO) throws Exception {

        Branch exiting=branchRepository.findById(id).orElseThrow(
                () -> new Exception("branch not exist..")

        );
          exiting.setName(branchDTO.getName());
          exiting.setWorkingDays(branchDTO.getWorkingDays());
          exiting.setEmail(branchDTO.getEmail());
          exiting.setPhone(branchDTO.getPhone());
          exiting.setAddress(branchDTO.getAddress());
          exiting.setOpenTime(branchDTO.getOpenTime());
          exiting.setCloseTime(branchDTO.getCloseTime());
          exiting.setUpdatedAt(LocalDateTime.now());

          Branch updatedBranch=branchRepository.save(exiting);
        return BranchMapper.toDTO(updatedBranch);
    }



    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch exiting=branchRepository.findById(id).orElseThrow(
                () -> new Exception("branch not exist..")

        );
        branchRepository.delete(exiting);
    }

    @Override
    public List<BranchDTO> getAllBranchesByStoreId(Long storeId) {
        List<Branch> branches=branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BranchDTO getBranchById(Long id) throws Exception {
        Branch exiting=branchRepository.findById(id).orElseThrow(
                () -> new Exception("branch not exist..")

        );
        return BranchMapper.toDTO(exiting) ;
    }
}
