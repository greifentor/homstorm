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
 *         GENERATED CODE!!! :o) DO NOT CHANGE!!!
 */
@Accessors(chain = true)
@Data
@Entity(name = "Item")
@Table(name = "Item")
public class ItemDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private long id;
	@Column(name = "Description", unique = true)
	private String description;

}