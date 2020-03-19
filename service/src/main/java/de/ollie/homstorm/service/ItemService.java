package de.ollie.homstorm.service;

import java.util.Optional;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ItemSO;
import de.ollie.homstorm.service.so.ResultPageSO;

/**
 * An interface for a item service.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface ItemService {

	boolean delete(long id) throws PersistenceException;

	ResultPageSO<ItemSO> findAll() throws PersistenceException;

	Optional<ItemSO> findById(long id) throws PersistenceException;

	void save(ItemSO item) throws PersistenceException;

}