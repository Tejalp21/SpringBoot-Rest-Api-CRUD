package com.prowings.service;

import java.util.List;

import com.prowings.entity.Product;

public interface ProductService {

	public boolean createProduct(Product product);

	public List<Product> getAllProducts();

	public boolean deleteProductById(int id);

	public Product updateProduct(Product product);
	

}
