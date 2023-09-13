package com.prowings.repository;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prowings.entity.Product;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Repository
public class ProductRepositoryImpl implements ProductRepository{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean createProduct(Product product) {
		log.info("call from service to repository in create product");
		
		try {
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			session.save(product);
			txn.commit();
			session.close();
			log.info("product saved successfully in DB");
			return true;
		}
		catch(Exception e)
		{
			log.info("Error occured while saving the product in DB");
			return false;
		}
		
	}

	@Override
	public List<Product> getAllProducts() {
		log.info("inside repository get all products");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Product> productList = null;
		try {

			tx = session.beginTransaction();
			Query<Product> query = session.createQuery("from Product");
			productList = query.list();
			tx.commit();
			log.info("Products fetched from DB successfully..");

		} catch (HibernateException ex) {
			if (tx != null) {
				tx.rollback();
			}
			log.info("Error occured while fetching products from DB: " + ex.getMessage());
			ex.printStackTrace(System.err);
		} 
		finally 
		{
			session.close();
			return productList;
		}


	}

	@Override
	public boolean deleteProductById(int id) {
		log.info("inside repository in delete product by id");
		
		try {
			Session session = sessionFactory.openSession();
			Transaction txn = session.beginTransaction();
			Product prod = session.get(Product.class,id);
			session.delete(prod);
			txn.commit();
			session.close();
			log.info("product deleted successfullyfrom DB");
			return true;
		}
		catch(Exception e)
		{
			log.info("Error occured while saving the product in DB");
			return false;
		}
	}

	@Override
	public Product updateProduct(Product product) {
		log.info("inside repository in update product ");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Product retrievedProduct = null;
		try {

			tx = session.beginTransaction();
			retrievedProduct = session.get(Product.class, product.getId());

			if (retrievedProduct != null) {
				log.info("found existing product.. Updating it!!");
				retrievedProduct.setName(product.getName());
				retrievedProduct.setPrice(product.getPrice());
				session.update(retrievedProduct);
				log.info("Updated successfully!!");
			} else {
				log.info("Since student was not present - creating it!!");
				session.save(product);
			}
			tx.commit();

		} catch (HibernateException ex) {
			System.out.println("Unable to update student : " + ex.getMessage());
		}

		return retrievedProduct;
	}

}
