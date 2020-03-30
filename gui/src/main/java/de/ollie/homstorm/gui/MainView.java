package de.ollie.homstorm.gui;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.VaadinSessionScope;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventListener;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.gui.events.EventType;
import de.ollie.homstorm.service.BestBeforeDateService;
import de.ollie.homstorm.service.ItemService;
import de.ollie.homstorm.service.ProductService;
import de.ollie.homstorm.service.StoragePlaceService;
import de.ollie.homstorm.service.UserService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;

/**
 * The main view of the HOMe STORage Manager application.
 *
 * @author ollie (19.03.2020)
 */
@Route
@PreserveOnRefresh
@VaadinSessionScope
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout implements EventListener {

	private final BestBeforeDateService bestBeforeDataService;
	private final EventProvider eventProvider;
	private final ItemService itemService;
	private final ProductService productService;
	private final StoragePlaceService storagePlaceService;

	public MainView(BestBeforeDateService bestBeforeDataService, EventProvider eventProvider, ItemService itemService,
			ProductService productService, StoragePlaceService storagePlaceService, UserService userService) {
		super();
		this.bestBeforeDataService = bestBeforeDataService;
		this.eventProvider = eventProvider;
		this.itemService = itemService;
		this.productService = productService;
		this.storagePlaceService = storagePlaceService;
		this.eventProvider.addListener(this);
		LoginView loginView = new LoginView(eventProvider, userService);
		// addClassName("centered-content");
		add( //
				loginView //
		);
	}

	@Override
	public void eventDetected(Event event) {
		if (event.getType() == EventType.USER_ACCEPTED) {
			removeAll();
			List<Component> view = new ArrayList<>();
			view.add(new ProductView(this.eventProvider, this.itemService, this.productService,
					this.storagePlaceService));
			view.add(new ItemView(this.eventProvider, this.itemService));
			view.add(new StoragePlaceView(this.eventProvider, this.storagePlaceService));
			view.add(new BestBeforeWarningView(this.eventProvider, this.bestBeforeDataService));
			Accordion accordion = new Accordion();
			accordion.add("Products", view.get(0));
			accordion.add("Items", view.get(1));
			accordion.add("StoragePlaces", view.get(2));
			accordion.add("Warnings", view.get(3));
			accordion.setWidthFull();
			accordion.addOpenedChangeListener(e -> e.getOpenedIndex().ifPresent(i -> {
				if (view.get(i) instanceof UpdatableView) {
					try {
						((UpdatableView) view.get(i)).updateView();
					} catch (PersistenceException pe) {
						System.out.println(pe.getMessage());
					}
				}
			}));
			add( //
					accordion //
			);
		}
	}

}