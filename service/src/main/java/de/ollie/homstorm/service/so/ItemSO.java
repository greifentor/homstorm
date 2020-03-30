package de.ollie.homstorm.service.so;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A service object class for items.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Accessors(chain = true)
@Data
public class ItemSO {

	private long id;
	private String description;
	private Double meals;
	private Integer messageDaysBeforeBestBeforeDate;

}