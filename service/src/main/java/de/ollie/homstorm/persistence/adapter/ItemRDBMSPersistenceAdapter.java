package de.ollie.homstorm.persistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.homstorm.persistence.converter.ItemDBOConverter;
import de.ollie.homstorm.persistence.dbo.ItemDBO;
import de.ollie.homstorm.persistence.repository.ItemRepository;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.persistence.port.ItemPersistencePort;
import de.ollie.homstorm.service.so.ItemSO;

/**
 * An implementation of the item persistence port interface for RDBMS.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class ItemRDBMSPersistenceAdapter implements ItemPersistencePort {

	private final ItemDBOConverter itemDBOConverter;
	private final ItemRepository itemRepository;

	public ItemRDBMSPersistenceAdapter(ItemDBOConverter itemDBOConverter, ItemRepository itemRepository) {
		super();
		this.itemDBOConverter = itemDBOConverter;
		this.itemRepository = itemRepository;
	}

	@Override
	public boolean delete(long id) throws PersistenceException {
		boolean result = false;
		try {
			Optional<ItemDBO> dbo = this.itemRepository.findById(id);
			if (dbo.isPresent()) {
				this.itemRepository.delete(dbo.get());
				result = true;
			}
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError, "error while deleting item with id: " + id, e);
		}
		return result;
	}

	@Override
	public List<ItemSO> findAll() throws PersistenceException {
		try {
			List<ItemSO> sos = new ArrayList<>();
			for (ItemDBO dbo : this.itemRepository.findAll()) {
				sos.add(this.itemDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding all items.", e);
		}
	}

	@Override
	public Optional<ItemSO> findById(long id) throws PersistenceException {
		try {
			Optional<ItemDBO> dbo = this.itemRepository.findById(id);
			if (dbo.isEmpty()) {
				return Optional.empty();
			}
			return Optional.of(this.itemDBOConverter.convertDBOToSO(dbo.get()));
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding by id: " + id, e);
		}
	}

	@Override
	public void save(ItemSO so) throws PersistenceException {
		try {
			ItemDBO dbo = this.itemDBOConverter.convertSOToDBO(so);
			this.itemRepository.save(dbo);
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError, "error while saving: " + so, e);
		}
	}

}