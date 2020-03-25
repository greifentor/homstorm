package de.ollie.homstorm.service;

import java.util.Optional;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.StoragePlaceSO;
import de.ollie.homstorm.service.so.ResultPageSO;

/**
 * An interface for a storageplace service.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface StoragePlaceService {

	boolean delete(long id) throws PersistenceException;

	ResultPageSO<StoragePlaceSO> findAll() throws PersistenceException;

	Optional<StoragePlaceSO> findById(long id) throws PersistenceException;

	void save(StoragePlaceSO storagePlace) throws PersistenceException;

}