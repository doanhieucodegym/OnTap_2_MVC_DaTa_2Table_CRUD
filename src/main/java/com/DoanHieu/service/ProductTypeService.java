package com.DoanHieu.service;

import com.DoanHieu.model.ProductType;

public interface ProductTypeService {
    Iterable<ProductType> findAll();
    ProductType findById(Long id);
    void save(ProductType productType);
    void remove(Long id);
}
