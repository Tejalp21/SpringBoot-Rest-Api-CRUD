package com.prowings.controller;

import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.prowings.util.Constants.*;

import java.util.List;

import com.prowings.entity.Product;
import com.prowings.service.ProductService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/products")
	public ResponseEntity<String> createProduct(@RequestBody Product product){
		log.info("Request received in controller to store the Product : {}", product);

		String createdProduct = (productService.createProduct(product)) ? SUCCESSFULLY_STORED : ERROR_WHILE_STORING;
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		log.info("Request received in controller to get all the Products : ");
		
		List<Product> fetchedProducts = (productService.getAllProducts()); 
		return new ResponseEntity<>(fetchedProducts, HttpStatus.OK);
	}
	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProductById(@PathVariable int id){
		log.info("Request received in controller to delete Product by Id : ");
		
		String deletedProduct = (productService.deleteProductById(id)) ? SUCCESSFULLY_DELETED : ERROR_WHILE_DELETING; 
		return new ResponseEntity<>(deletedProduct, HttpStatus.OK);
	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product){
		log.info("Request received in controller to update Product : ");
		
		Product updatedProduct = productService.updateProduct(product); 
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

}
