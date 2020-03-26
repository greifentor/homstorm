package de.ollie.homstorm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.ollie.homstorm.persistence.dbo.ProductDBO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

/**
 * A CRUD repository for product access.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Repository
public interface ProductRepository extends CrudRepository<ProductDBO, Long> {

	@Query("SELECT p FROM Product p WHERE p.item.id=?1")
	List<ProductDBO> findProductsForItem(long itemId);

	@Query("SELECT p FROM Product p WHERE p.storagePlace.id=?1")
	List<ProductDBO> findProductsForStoragePlace(long storagePlaceId);

}