package de.ollie.homstorm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.homstorm.service.StoragePlaceService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.persistence.port.StoragePlacePersistencePort;
import de.ollie.homstorm.service.so.StoragePlaceSO;
import de.ollie.homstorm.service.so.ResultPageSO;

/**
 * An implementation of the storageplace service interface.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class StoragePlaceServiceImpl implements StoragePlaceService {

	private final StoragePlacePersistencePort storagePlacePersistencePort;

	public StoragePlaceServiceImpl(StoragePlacePersistencePort storagePlacePersistencePort) {
		super();
		this.storagePlacePersistencePort = storagePlacePersistencePort;
	}

	@Override
	public boolean delete(long id) throws PersistenceException {
		return this.storagePlacePersistencePort.delete(id);
	}

	@Override
	public ResultPageSO<StoragePlaceSO> findAll() throws PersistenceException {
		List<StoragePlaceSO> l = this.storagePlacePersistencePort.findAll();
		return new ResultPageSO<StoragePlaceSO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l).setTotalResults(l.size());
	}

	@Override
	public Optional<StoragePlaceSO> findById(long id) throws PersistenceException {
		return this.storagePlacePersistencePort.findById(id);
	}

	@Override
	public void save(StoragePlaceSO storagePlace) throws PersistenceException {
		this.storagePlacePersistencePort.save(storagePlace);
	}

}