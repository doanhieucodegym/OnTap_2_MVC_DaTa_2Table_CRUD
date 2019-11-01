package com.DoanHieu.repository;

import com.DoanHieu.model.Product;
import com.DoanHieu.model.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {
 Iterable<Product> findAllByProductType(Product product);
 Page<Product> findAllByProductTypeName(String name, Pageable pageable);
}
