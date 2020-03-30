package de.ollie.homstorm.gui;

import de.ollie.homstorm.service.persistence.exception.PersistenceException;

/**
 * An interface for updatable views.
 *
 * @author ollie (30.03.2020)
 */
public interface UpdatableView {

	void updateView() throws PersistenceException;

}