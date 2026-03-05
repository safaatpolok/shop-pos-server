package com.shop.Repositorty;

import com.shop.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findByStoreId(Long storeId);
}
