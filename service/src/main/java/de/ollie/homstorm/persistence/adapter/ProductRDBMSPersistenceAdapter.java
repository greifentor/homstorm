package de.ollie.homstorm.persistence.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.homstorm.persistence.converter.ProductDBOConverter;
import de.ollie.homstorm.persistence.dbo.ProductDBO;
import de.ollie.homstorm.persistence.repository.ProductRepository;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.persistence.port.ProductPersistencePort;
import de.ollie.homstorm.service.so.ProductSO;

/**
 * An implementation of the product persistence port interface for RDBMS.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class ProductRDBMSPersistenceAdapter implements ProductPersistencePort {

	private final ProductDBOConverter productDBOConverter;
	private final ProductRepository productRepository;

	public ProductRDBMSPersistenceAdapter(ProductDBOConverter productDBOConverter,
			ProductRepository productRepository) {
		super();
		this.productDBOConverter = productDBOConverter;
		this.productRepository = productRepository;
	}

	@Override
	public boolean delete(Long id) throws PersistenceException {
		boolean result = false;
		try {
			Optional<ProductDBO> dbo = this.productRepository.findById(id);
			if (dbo.isPresent()) {
				this.productRepository.delete(dbo.get());
				result = true;
			}
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError,
					"error while deleting product with id: " + id, e);
		}
		return result;
	}

	@Override
	public List<ProductSO> findAll() throws PersistenceException {
		try {
			List<ProductSO> sos = new ArrayList<>();
			for (ProductDBO dbo : this.productRepository.findAll()) {
				sos.add(this.productDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding all products.", e);
		}
	}

	@Override
	public Optional<ProductSO> findById(long id) throws PersistenceException {
		try {
			Optional<ProductDBO> dbo = this.productRepository.findById(id);
			if (dbo.isEmpty()) {
				return Optional.empty();
			}
			return Optional.of(this.productDBOConverter.convertDBOToSO(dbo.get()));
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError, "error while finding by id: " + id, e);
		}
	}

	@Override
	public void save(ProductSO so) throws PersistenceException {
		try {
			ProductDBO dbo = this.productDBOConverter.convertSOToDBO(so);
			this.productRepository.save(dbo);
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.WriteError, "error while saving: " + so, e);
		}
	}

	@Override
	public List<ProductSO> findProductsForItem(long itemId) throws PersistenceException {
		try {
			List<ProductSO> sos = new ArrayList<>();
			for (ProductDBO dbo : this.productRepository.findProductsForItem(itemId)) {
				sos.add(this.productDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError,
					"error while finding all products for item:" + itemId, e);
		}
	}

	@Override
	public List<ProductSO> findProductsForStoragePlace(long storagePlaceId) throws PersistenceException {
		try {
			List<ProductSO> sos = new ArrayList<>();
			for (ProductDBO dbo : this.productRepository.findProductsForStoragePlace(storagePlaceId)) {
				sos.add(this.productDBOConverter.convertDBOToSO(dbo));
			}
			return sos;
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.Type.ReadError,
					"error while finding all products for storagePlace:" + storagePlaceId, e);
		}
	}

}