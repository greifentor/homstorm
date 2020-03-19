package de.ollie.homstorm.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import de.ollie.homstorm.persistence.dbo.ItemDBO;

/**
 * A CRUD repository for item access.
 *
 * @author rest-acf
 *
 *         GENERATED CODE!!! DO NOT CHANGE!!!
 */
@Repository
public interface ItemRepository extends CrudRepository<ItemDBO, Long>, JpaRepository<ItemDBO, Long> {
}