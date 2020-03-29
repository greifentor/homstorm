package de.ollie.homstorm.persistence.dbo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A ORM mapping and database access class for items.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Accessors(chain = true)
@Data
@Entity(name = "Item")
@Table(name = "ITEM")
public class ItemDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ITEM_IDS")
	@Column(name = "ID")
	private long id;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "MESSAGE_DAYS_BEFORE_BEST_BEFORE_DATE")
	private Integer messageDaysBeforeBestBeforeDate;

}