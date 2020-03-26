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
 * A ORM mapping and database access class for storage places.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Accessors(chain = true)
@Data
@Entity(name = "StoragePlace")
@Table(name = "StoragePlace")
public class StoragePlaceDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "storagePlaceIds")
	@Column(name = "Id")
	private long id;
	@Column(name = "Description")
	private String description;

}