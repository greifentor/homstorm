package de.ollie.homstorm.gui;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PreserveOnRefresh;
import com.vaadin.flow.router.Route;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventListener;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.gui.events.EventType;
import de.ollie.homstorm.service.BestBeforeDateService;
import de.ollie.homstorm.service.ItemService;
import de.ollie.homstorm.service.ProductService;
import de.ollie.homstorm.service.StoragePlaceService;
import de.ollie.homstorm.service.UserService;

/**
 * The main view of the HOMe STORage Manager application.
 *
 * @author ollie (19.03.2020)
 */
@Route
@PreserveOnRefresh
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
			Accordion accordion = new Accordion();
			accordion.add("Products", new ProductView(this.eventProvider, this.itemService, this.productService,
					this.storagePlaceService));
			accordion.add("Items", new ItemView(this.eventProvider, this.itemService));
			accordion.add("StoragePlaces", new StoragePlaceView(this.eventProvider, this.storagePlaceService));
			accordion.add("Warnings", new BestBeforeWarningView(this.eventProvider, this.bestBeforeDataService));
			accordion.setWidthFull();
			add( //
					accordion //
			);
		}
	}

}