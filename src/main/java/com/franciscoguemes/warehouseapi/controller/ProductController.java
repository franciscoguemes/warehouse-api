package com.franciscoguemes.warehouseapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.franciscoguemes.warehouseapi.model.Product;
import com.franciscoguemes.warehouseapi.service.ProductService;

@RestController
@RequestMapping(ProductController.BASE_URL)
public class ProductController {
	
	public static final String BASE_URL="/api/v1/products";

	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping
	List<Product> getAllProducts(){
		return this.productService.findAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		Optional<Product> optional = this.productService.findProductById(id);
		return optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Product modifyProduct(@PathVariable Long id, @RequestBody Product product) {
		validate(product);
		
		Optional<Product> optional = this.productService.findProductById(id);
		Product oldProduct = optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
		
		Product newProduct = productService.modifyProduct(oldProduct, product);
		
		return newProduct;
	}
	
	private void validate(Product product) {
		
		if(product.getName()==null && product.getDescription()==null && product.getPrice()==null ) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The parameters can not be null"); 
		}

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduct(@PathVariable Long id) {
		Optional<Product> optional = this.productService.findProductById(id);
		Product product = optional.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
		productService.deleteProduct(product.getId());
	}
	
}
