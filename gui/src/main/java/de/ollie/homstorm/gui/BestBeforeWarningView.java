package de.ollie.homstorm.gui;

import java.time.LocalDate;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventListener;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.service.BestBeforeDateService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ProductSO;

/**
 * A view for products which gone in problems with their best before date.
 *
 * @author ollie (29.03.2020)
 */
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class BestBeforeWarningView extends VerticalLayout implements EventListener {

	private final BestBeforeDateService bestBeforeDateService;

	private Grid<ProductSO> gridProductWarnings = new Grid<>(10);

	public BestBeforeWarningView(EventProvider eventProvider, BestBeforeDateService bestBeforeDateService) {
		super();
		this.bestBeforeDateService = bestBeforeDateService;
		eventProvider.addListener(this);
		getStyle().set("border", "1px solid LightGray");
		gridProductWarnings.addColumn(product -> product.getItem().getDescription()).setHeader("Item");
		gridProductWarnings.addColumn(product -> product.getStoragePlace().getDescription()).setHeader("Storage Place");
		gridProductWarnings.addColumn(ProductSO::getBestBeforeDate).setHeader("Best Before Date");
		gridProductWarnings.setWidthFull();
		add( //
				gridProductWarnings //
		);
		updateGrid();
	}

	private void updateGrid() {
		try {
			this.gridProductWarnings.setItems(this.bestBeforeDateService.getProductWarnings(LocalDate.now()));
		} catch (PersistenceException pe) {
			pe.printStackTrace();
		}
	}

	@Override
	public void eventDetected(Event event) {
		updateGrid();
	}

}