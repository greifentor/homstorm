package de.ollie.homstorm.service.so;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * A service object class for products.
 *
 * @author rest-acf
 *
 * GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Accessors(chain = true)
@Data
public class ProductSO {

	private Long id;
	private ItemSO item;
	private StoragePlaceSO storagePlace;
	private LocalDate bestBeforeDate;

}