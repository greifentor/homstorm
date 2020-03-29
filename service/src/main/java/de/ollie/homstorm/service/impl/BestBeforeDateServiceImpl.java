package de.ollie.homstorm.service.impl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import de.ollie.homstorm.service.BestBeforeDateService;
import de.ollie.homstorm.service.ProductService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ProductSO;

/**
 * An implementation of a service for best before date management.
 *
 * @author ollie (29.03.2020)
 */
@Named
public class BestBeforeDateServiceImpl implements BestBeforeDateService {

	private final ProductService productService;

	public BestBeforeDateServiceImpl(ProductService productService) {
		super();
		this.productService = productService;
	}

	@Override
	public List<ProductSO> getProductWarnings(LocalDate date) throws PersistenceException {
		List<ProductSO> l = new ArrayList<>();
		this.productService.findAll().getResults() //
				.forEach(product -> {
					if (date.isAfter(product.getBestBeforeDate()
							.minus(product.getItem().getMessageDaysBeforeBestBeforeDate(), ChronoUnit.DAYS))) {
						l.add(product);
					}
				}) //
		;
		return l;
	}

}