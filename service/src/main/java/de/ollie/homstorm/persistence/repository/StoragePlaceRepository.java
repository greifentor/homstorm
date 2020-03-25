package de.ollie.homstorm.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.ollie.homstorm.persistence.dbo.StoragePlaceDBO;

/**
 * A CRUD repository for storageplace access.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Repository
public interface StoragePlaceRepository extends CrudRepository<StoragePlaceDBO, Long> {
}