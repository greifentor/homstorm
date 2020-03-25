package de.ollie.homstorm.gui;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * The main view of the HOMe STORage Manager application.
 *
 * @author ollie (19.03.2020)
 */
@Route
@Named
@Scope("session")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

	private final ItemView itemView;
	private final StoragePlaceView storagePlaceView;

	public MainView(ItemView itemView, StoragePlaceView storagePlaceView) {
		super();
		this.itemView = itemView;
		this.storagePlaceView = storagePlaceView;
		Accordion accordion = new Accordion();
		accordion.setWidthFull();
		accordion.add("Items", this.itemView);
		accordion.add("StoragePlaces", this.storagePlaceView);
		addClassName("centered-content");
		add( //
				accordion //
		);
	}

}