package de.ollie.homstorm.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import de.ollie.homstorm.service.ProductService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.persistence.port.ProductPersistencePort;
import de.ollie.homstorm.service.so.ProductSO;
import de.ollie.homstorm.service.so.ResultPageSO;

/**
 * An implementation of the product service interface.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductPersistencePort productPersistencePort;

	public ProductServiceImpl(ProductPersistencePort productPersistencePort) {
		super();
		this.productPersistencePort = productPersistencePort;
	}

	@Override
	public boolean delete(Long id) throws PersistenceException {
		return this.productPersistencePort.delete(id);
	}

	@Override
	public ResultPageSO<ProductSO> findAll() throws PersistenceException {
		List<ProductSO> l = this.productPersistencePort.findAll();
		return new ResultPageSO<ProductSO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l).setTotalResults(l.size());
	}

	@Override
	public Optional<ProductSO> findById(long id) throws PersistenceException {
		return this.productPersistencePort.findById(id);
	}

	@Override
	public void save(ProductSO product) throws PersistenceException {
		this.productPersistencePort.save(product);
	}

	@Override
	public ResultPageSO<ProductSO> findProductsForItem(long itemId) throws PersistenceException {
		List<ProductSO> l = this.productPersistencePort.findProductsForItem(itemId);
		return new ResultPageSO<ProductSO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l).setTotalResults(l.size());
	}

	@Override
	public ResultPageSO<ProductSO> findProductsForStoragePlace(long storagePlaceId) throws PersistenceException {
		List<ProductSO> l = this.productPersistencePort.findProductsForStoragePlace(storagePlaceId);
		return new ResultPageSO<ProductSO>().setCurrentPage(0).setResultsPerPage(l.size()).setResults(l).setTotalResults(l.size());
	}

}