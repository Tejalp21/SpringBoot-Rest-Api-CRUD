package com.prowings.repository;

import java.util.List;

import com.prowings.entity.Product;

public interface ProductRepository {

	public boolean createProduct(Product product);

	public List<Product> getAllProducts();

	public boolean deleteProductById(int id);

	public Product updateProduct(Product product);

}
