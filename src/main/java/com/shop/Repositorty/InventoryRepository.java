package com.shop.Repositorty;

import com.shop.modal.Inventory;
import com.shop.modal.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProductIdAndBranchId(Long productId, Long branchId);
    List<Inventory> findByBranchId(Long branchId);


    @Query("""
            select count(i)
            from Inventory i
            join i.product p
            where i.branch.id=:branchId
            AND i.quantity<=5
            """)


    int countLowerStockItems(@Param("branchId") Long BranchId);
}
