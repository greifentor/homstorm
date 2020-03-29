package de.ollie.homstorm.service;

import java.time.LocalDate;
import java.util.List;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ProductSO;

/**
 * A service with methods for best before date processing.
 *
 * @author ollie (29.03.2020)
 */
public interface BestBeforeDateService {

	/**
	 * Returns infos about all products which will reach their best before date in the next time related to the passed
	 * date. These are all those products which passed the best before date minus the amount of days which are
	 * configured in the item (field "messageDaysBeforeBestBeforeDate").
	 * 
	 * @param date The date which will be used to process the list.
	 * @return A list of all products which are in the range or an empty list if no products are in the range.
	 * @throws PersistenceException If an error occurs while accessing the database.
	 */
	List<ProductSO> getProductWarnings(LocalDate date) throws PersistenceException;

}