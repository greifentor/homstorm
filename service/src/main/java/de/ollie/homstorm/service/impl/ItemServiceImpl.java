package de.ollie.homstorm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.homstorm.service.ItemService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.persistence.port.ItemPersistencePort;
import de.ollie.homstorm.service.so.ItemSO;
import de.ollie.homstorm.service.so.ResultPageSO;

/**
 * An implementation of the item service interface.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemPersistencePort itemPersistencePort;

	public ItemServiceImpl(ItemPersistencePort itemPersistencePort) {
		super();
		this.itemPersistencePort = itemPersistencePort;
	}

	@Override
	public boolean delete(long id) throws PersistenceException {
		return this.itemPersistencePort.delete(id);
	}

	@Override
	public ResultPageSO<ItemSO> findAll() throws PersistenceException {
		List<ItemSO> l = this.itemPersistencePort.findAll();
		return new ResultPageSO<ItemSO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l).setTotalResults(l.size());
	}

	@Override
	public Optional<ItemSO> findById(long id) throws PersistenceException {
		return this.itemPersistencePort.findById(id);
	}

	@Override
	public void save(ItemSO item) throws PersistenceException {
		this.itemPersistencePort.save(item);
	}

}