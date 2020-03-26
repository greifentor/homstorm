package de.ollie.homstorm.service.persistence.port;

import java.util.List;
import java.util.Optional;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ProductSO;

/**
 * An interface for product persistence ports.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface ProductPersistencePort {

	boolean delete(Long id) throws PersistenceException;

	List<ProductSO> findAll() throws PersistenceException;

	Optional<ProductSO> findById(long id) throws PersistenceException;

	void save(ProductSO so) throws PersistenceException;

	List<ProductSO> findProductsForItem(long itemId) throws PersistenceException;

	List<ProductSO> findProductsForStoragePlace(long storagePlaceId) throws PersistenceException;

}