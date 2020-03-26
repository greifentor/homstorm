package de.ollie.homstorm.gui;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventListener;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.gui.events.EventType;

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
public class MainView extends VerticalLayout implements EventListener {

	private final EventProvider eventProvider;
	private final ItemView itemView;
	private final LoginView loginView;
	private final ProductView productView;
	private final StoragePlaceView storagePlaceView;

	public MainView(EventProvider eventProvider, ItemView itemView, LoginView loginView, ProductView productView,
			StoragePlaceView storagePlaceView) {
		super();
		this.eventProvider = eventProvider;
		this.itemView = itemView;
		this.loginView = loginView;
		this.productView = productView;
		this.storagePlaceView = storagePlaceView;
		this.eventProvider.addListener(this);
		addClassName("centered-content");
		add( //
				this.loginView //
		);
	}

	@Override
	public void eventDetected(Event event) {
		if (event.getType() == EventType.USER_ACCEPTED) {
			removeAll();
			Accordion accordion = new Accordion();
			accordion.add("Products", this.productView);
			accordion.add("Items", this.itemView);
			accordion.add("StoragePlaces", this.storagePlaceView);
			accordion.setWidthFull();
			add( //
					accordion //
			);
		}
	}

}