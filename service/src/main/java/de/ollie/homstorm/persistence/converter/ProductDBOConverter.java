package de.ollie.homstorm.persistence.converter;

import org.springframework.stereotype.Component;

import de.ollie.homstorm.persistence.dbo.ProductDBO;
import de.ollie.homstorm.service.so.ProductSO;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * A converter for product DBO's.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Component
public class ProductDBOConverter {

	@Autowired
	private ItemDBOConverter itemDBOConverter;
	@Autowired
	private StoragePlaceDBOConverter storagePlaceDBOConverter;

	public ProductSO convertDBOToSO(ProductDBO dbo) {
		if (dbo == null) {
			return null;
		}
		return new ProductSO().setId(dbo.getId()).setItem(this.itemDBOConverter.convertDBOToSO(dbo.getItem())).setStoragePlace(this.storagePlaceDBOConverter.convertDBOToSO(dbo.getStoragePlace())).setBestBeforeDate(dbo.getBestBeforeDate());
	}

	public ProductDBO convertSOToDBO(ProductSO so) {
		if (so == null) {
			return null;
		}
		return new ProductDBO().setId(so.getId()).setItem(this.itemDBOConverter.convertSOToDBO(so.getItem())).setStoragePlace(this.storagePlaceDBOConverter.convertSOToDBO(so.getStoragePlace())).setBestBeforeDate(so.getBestBeforeDate());
	}

}