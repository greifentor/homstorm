package de.ollie.homstorm.persistence.dbo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * A ORM mapping and database access class for products.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Accessors(chain = true)
@Data
@Entity(name = "Product")
@Table(name = "PRODUCT")
public class ProductDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUCT_IDS")
	@Column(name = "ID")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ITEM", referencedColumnName = "ID")
	private ItemDBO item;
	@ManyToOne
	@JoinColumn(name = "STORAGE_PLACE", referencedColumnName = "ID")
	private StoragePlaceDBO storagePlace;
	@Column(name = "BEST_BEFORE_DATE")
	private LocalDate bestBeforeDate;

}