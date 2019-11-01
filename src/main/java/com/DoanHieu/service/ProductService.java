package com.DoanHieu.service;

import com.DoanHieu.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Iterable<Product> findAll();

    Product findById(Long id);
    void save(Product product);
    void remove(Long id);
    Iterable<Product> findAllByProvince(Product product);
    Page<Product> findAllByProductTypeName(String name, Pageable pageable);
    Page<Product> findAll(Pageable pageable);

}
