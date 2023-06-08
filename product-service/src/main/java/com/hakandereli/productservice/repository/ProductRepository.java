package com.hakandereli.productservice.repository;

import com.hakandereli.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product , String> {
}
