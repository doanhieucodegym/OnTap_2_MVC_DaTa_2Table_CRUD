package com.DoanHieu.service;

import com.DoanHieu.model.ProductType;
import com.DoanHieu.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public Iterable<ProductType> findAll() {
        return productTypeRepository.findAll();
    }

    @Override
    public ProductType findById(Long id) {
        return productTypeRepository.findOne(id);
    }

    @Override
    public void save(ProductType productType) {
    productTypeRepository.save(productType);
    }

    @Override
    public void remove(Long id) {
    productTypeRepository.delete(id);
    }
}
