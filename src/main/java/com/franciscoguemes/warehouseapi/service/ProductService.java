package com.franciscoguemes.warehouseapi.service;

import java.util.List;
import java.util.Optional;

import com.franciscoguemes.warehouseapi.model.Product;

public interface ProductService {

	Optional<Product> findProductById(Long id);
	
	List<Product> findAllProducts();

	Product saveProduct(Product product);

	void deleteProduct(Long id);

	Product modifyProduct(Product oldProduct, Product product);
	
}
