package com.shop.service.impl;

import com.shop.Repositorty.BranchRepository;
import com.shop.Repositorty.InventoryRepository;
import com.shop.Repositorty.ProductRepository;
import com.shop.mapper.InventoryMapper;
import com.shop.modal.Branch;
import com.shop.modal.Inventory;
import com.shop.modal.Product;
import com.shop.payload.dto.InventoryDTO;
import com.shop.service.InventoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class InventoryServicesImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;
    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO) throws Exception {

        Branch branch=branchRepository.findById(inventoryDTO.getBranchId()).orElseThrow(
                () -> new Exception("branch not exist..")

        );
        Product product=productRepository.findById(inventoryDTO.getProductId()).orElseThrow(
                () -> new Exception("product not exist..")
        );

        Inventory inventory= InventoryMapper.toEntity(inventoryDTO,branch,product);
        Inventory savedInventory=inventoryRepository.save(inventory);


        return InventoryMapper.toDTO(savedInventory) ;
    }


    @Override
    public InventoryDTO updateInventory(Long id,InventoryDTO inventoryDTO) throws Exception {

        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("inventory not found..")
        );
         inventory.setQuantity(inventoryDTO.getQuantity());
         Inventory updatedInventory=inventoryRepository.save(inventory);
        return InventoryMapper.toDTO(updatedInventory) ;
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("inventory not found..")
        );
        inventoryRepository.delete(inventory);


    }

    @Override
    public InventoryDTO getInventoryById(Long id) throws Exception {

        Inventory inventory=inventoryRepository.findById(id).orElseThrow(
                ()->new Exception("inventory not found..")
        );
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDTO getInventoryByProductIdAndBranchId(Long productId, Long brandId) {
        Inventory inventory=inventoryRepository.findByProductIdAndBranchId(productId,brandId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDTO> getAllInventoryByBranchId(Long branchId) {
        List<Inventory> inventories=inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(
                InventoryMapper::toDTO
        ).collect(Collectors.toList());
    }
}
