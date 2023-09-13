package com.prowings.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prowings.entity.Product;
import com.prowings.repository.ProductRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public boolean createProduct(Product product) {
		log.info("call from controller to service...");
		return productRepository.createProduct(product);
	}

	@Override
	public List<Product> getAllProducts() {
		log.info("inside service get all products...");
		return productRepository.getAllProducts();
	}

	@Override
	public boolean deleteProductById(int id) {
		log.info("inside service delete product by id...");
		return productRepository.deleteProductById(id);
	
	}

	@Override
	public Product updateProduct(Product product) {
		log.info("inside service update product ...");
		return productRepository.updateProduct(product);
	}
	
	

}
