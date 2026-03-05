package com.shop.service.impl;

import com.shop.Repositorty.CategoryRepository;
import com.shop.Repositorty.ProductRepository;
import com.shop.Repositorty.StoreRepository;
import com.shop.mapper.ProductMapper;
import com.shop.modal.Category;
import com.shop.modal.Product;
import com.shop.modal.Store;
import com.shop.modal.User;
import com.shop.payload.dto.ProductDTO;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) throws Exception {
        Store store = storeRepository.findById(
                productDTO.getStoreId()
        ).orElseThrow(
                () -> new Exception("Store not found")
        );

        Category category =categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                () -> new Exception("Category not found")
        );

        Product product = ProductMapper.toEntity(productDTO, store,category);
        Product savedProduct =  productRepository.save(product);


        return ProductMapper.toDTO(savedProduct);
    }


    @Override
    public ProductDTO updateProduct( Long id,ProductDTO productDTO, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not found")
        );

        if(productDTO.getCategoryId()!=null){
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                    ()-> new Exception("category not found")
            );

            product.setCategory(category);



        }



        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setSku(productDTO.getSku());
        product.setImage(productDTO.getImage());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setBrand(productDTO.getBrand());
        product.setUpdatedAt(LocalDateTime.now());

        if(productDTO.getCategoryId()!=null){
            Category category = categoryRepository.findById(productDTO.getCategoryId()).orElseThrow(
                    ()-> new Exception("category not found")
            );

            product.setCategory(category);

        }

        Product savedProduct =  productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);


    }



    @Override
    public void deleteProduct(Long id, User user) throws Exception {

        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("product not fund")
        );
        productRepository.delete(product);

    }

    @Override
    public List<ProductDTO> getProductsByStoreID(Long storeID) {
        List<Product> products= productRepository.findByStoreId(storeID);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        List<Product> products= productRepository.searchByKeyword(storeId, keyword);
        return products.stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());


    }
}
