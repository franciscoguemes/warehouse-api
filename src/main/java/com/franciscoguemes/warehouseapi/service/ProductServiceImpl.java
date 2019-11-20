package com.franciscoguemes.warehouseapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.franciscoguemes.warehouseapi.model.Product;
import com.franciscoguemes.warehouseapi.repo.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Override
	public Optional<Product> findProductById(Long id) {
		return this.productRepository.findById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return this.productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		this.productRepository.deleteById(id);
		
	}

	@Override
	public Product modifyProduct(Product oldProduct, Product newProduct) {
		
		if(newProduct.getName() != null) {
			oldProduct.setName(newProduct.getName());
		}
		
		if(newProduct.getPrice()!= null) {
			oldProduct.setPrice(newProduct.getPrice());
		}
		
		if(newProduct.getDescription()!=null) {
			oldProduct.setDescription(newProduct.getDescription());
		}
		
		return this.productRepository.save(oldProduct);
	}

}
