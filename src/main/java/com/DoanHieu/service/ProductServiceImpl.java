package com.DoanHieu.service;

import com.DoanHieu.model.Product;
import com.DoanHieu.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProductServiceImpl implements ProductService {
 @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void save(Product product) {
    productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
    productRepository.delete(id);
    }

    @Override
    public Iterable<Product> findAllByProvince(Product product) {
        return productRepository.findAllByProductType(product);
    }

   @Override
   public Page<Product> findAllByProductTypeName(String name, Pageable pageable) {
      return productRepository.findAllByProductTypeName(name,pageable);
   }

   @Override
   public Page<Product> findAll(Pageable pageable) {
      return productRepository.findAll(pageable);
   }
}
