package de.ollie.homstorm.service.persistence.port;

import java.util.List;
import java.util.Optional;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ItemSO;

/**
 * An interface for item persistence ports.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
public interface ItemPersistencePort {

	boolean delete(long id) throws PersistenceException;

	List<ItemSO> findAll() throws PersistenceException;

	Optional<ItemSO> findById(long id) throws PersistenceException;

	void save(ItemSO so) throws PersistenceException;

}