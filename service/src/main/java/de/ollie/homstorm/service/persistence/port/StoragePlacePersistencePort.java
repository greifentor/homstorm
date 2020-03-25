package de.ollie.homstorm.service.persistence.port;

import java.util.List;
import java.util.Optional;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.StoragePlaceSO;

/**
 * An interface for storageplace persistence ports.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface StoragePlacePersistencePort {

	boolean delete(long id) throws PersistenceException;

	List<StoragePlaceSO> findAll() throws PersistenceException;

	Optional<StoragePlaceSO> findById(long id) throws PersistenceException;

	void save(StoragePlaceSO so) throws PersistenceException;

}