package de.ollie.homstorm.persistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.homstorm.persistence.converter.StoragePlaceDBOConverter;
import de.ollie.homstorm.persistence.dbo.StoragePlaceDBO;
import de.ollie.homstorm.persistence.repository.StoragePlaceRepository;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.persistence.port.StoragePlacePersistencePort;
import de.ollie.homstorm.service.so.StoragePlaceSO;

/**
 * An implementation of the storageplace persistence port interface for RDBMS.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class StoragePlaceRDBMSPersistenceAdapter implements StoragePlacePersistencePort {

	private final StoragePlaceDBOConverter storagePlaceDBOConverter;
	private final StoragePlaceRepository storagePlaceRepository;

	public StoragePlaceRDBMSPersistenceAdapter(StoragePlaceDBOConverter storagePlaceDBOConverter, StoragePlaceRepository storagePlaceRepository) {
		super();
		this.storagePlaceDBOConverter = storagePlaceDBOConverter;
		this.storagePlaceRepository = storagePlaceRepository;
	}

	@Override
	public boolean delete(long id) throws PersistenceException {
		boolean result = false;
		try {
			Optional<StoragePlaceDBO> dbo = this.storagePlaceRepository.findById(id);
			if (dbo.isPresent()) {
				this.storagePlaceRepository.delete(dbo.get());
				result = true;
			}
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError, "error while deleting storagePlace with id: " + id, e);
		}
		return result;
	}

	@Override
	public List<StoragePlaceSO> findAll() throws PersistenceException {
		try {
			List<StoragePlaceSO> sos = new ArrayList<>();
			for (StoragePlaceDBO dbo : this.storagePlaceRepository.findAll()) {
				sos.add(this.storagePlaceDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding all storagePlaces.", e);
		}
	}

	@Override
	public Optional<StoragePlaceSO> findById(long id) throws PersistenceException {
		try {
			Optional<StoragePlaceDBO> dbo = this.storagePlaceRepository.findById(id);
			if (dbo.isEmpty()) {
				return Optional.empty();
			}
			return Optional.of(this.storagePlaceDBOConverter.convertDBOToSO(dbo.get()));
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding by id: " + id, e);
		}
	}

	@Override
	public void save(StoragePlaceSO so) throws PersistenceException {
		try {
			StoragePlaceDBO dbo = this.storagePlaceDBOConverter.convertSOToDBO(so);
			this.storagePlaceRepository.save(dbo);
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError, "error while saving: " + so, e);
		}
	}

}