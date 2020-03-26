package de.ollie.homstorm.service;

import java.util.Optional;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ProductSO;
import de.ollie.homstorm.service.so.ResultPageSO;

/**
 * An interface for a product service.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface ProductService {

	boolean delete(Long id) throws PersistenceException;

	ResultPageSO<ProductSO> findAll() throws PersistenceException;

	Optional<ProductSO> findById(long id) throws PersistenceException;

	void save(ProductSO product) throws PersistenceException;

	ResultPageSO<ProductSO> findProductsForItem(long itemId) throws PersistenceException;

	ResultPageSO<ProductSO> findProductsForStoragePlace(long storagePlaceId) throws PersistenceException;

}