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
 * A ORM mapping and database access class for storageplaces.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Accessors(chain = true)
@Data
@Entity(name = "StoragePlace")
@Table(name = "STORAGE_PLACE")
public class StoragePlaceDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "STORAGE_PLACE_IDS")
	@Column(name = "ID")
	private long id;
	@Column(name = "DESCRIPTION")
	private String description;

}