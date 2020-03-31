package de.ollie.homstorm.gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventListener;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.gui.events.EventType;
import de.ollie.homstorm.service.ItemService;
import de.ollie.homstorm.service.ProductService;
import de.ollie.homstorm.service.StoragePlaceService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ItemSO;
import de.ollie.homstorm.service.so.ProductSO;
import de.ollie.homstorm.service.so.StoragePlaceSO;

/**
 * A view for product maintenance.
 *
 * @author ollie (26.03.2020)
 */
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ProductView extends VerticalLayout implements EventListener, UpdatableView {

	private final EventProvider eventProvider;
	private final ItemService itemService;
	private final ProductService productService;
	private final StoragePlaceService storagePlaceService;

	private Button buttonDelete = new Button("Delete");
	private Button buttonSave = new Button("Save");
	private Column<ProductSO> mealsColumn = null;
	private DatePicker datePickerBestBeforeDate = new DatePicker("Best Before Date");
	private Grid<ProductSO> gridProducts = new Grid<>(10);
	private TextField textFieldId = new TextField("Id");
	private ComboBox<ItemSO> comboBoxItem = new ComboBox<>(10);
	private ComboBox<StoragePlaceSO> comboBoxStoragePlace = new ComboBox<>(10);

	public ProductView(EventProvider eventProvider, ItemService itemService, ProductService productService,
			StoragePlaceService storagePlaceService) {
		super();
		this.eventProvider = eventProvider;
		this.itemService = itemService;
		this.productService = productService;
		this.storagePlaceService = storagePlaceService;
		this.eventProvider.addListener(this);
		getStyle().set("border", "1px solid LightGray");
		buttonDelete.addClickListener(event -> deleteItem(gridProducts.getSelectedItems()));
		buttonDelete.setSizeFull();
		buttonSave.addClickListener(event -> saveItem(comboBoxItem.getValue(), comboBoxStoragePlace.getValue(),
				datePickerBestBeforeDate.getValue(), textFieldId.getValue()));
		buttonSave.setSizeFull();
		datePickerBestBeforeDate.setValue(LocalDate.now());
		datePickerBestBeforeDate.setWidthFull();
		gridProducts.addColumn(product -> product.getItem().getDescription()).setHeader("Item");
		gridProducts.addColumn(product -> product.getStoragePlace().getDescription()).setHeader("Storage Place");
		mealsColumn = gridProducts.addColumn(product -> product.getItem().getMeals()).setHeader("Meals");
		gridProducts.addColumn(ProductSO::getBestBeforeDate).setHeader("Best Before Date");
		gridProducts.addItemDoubleClickListener(event -> putToEditor(event.getItem()));
		gridProducts.setWidthFull();
		comboBoxItem.setItems(new ArrayList<>());
		comboBoxItem.setItemLabelGenerator(ItemSO::getDescription);
		comboBoxItem.setLabel("Item");
		comboBoxItem.setSizeFull();
		comboBoxStoragePlace.setItems(new ArrayList<>());
		comboBoxStoragePlace.setItemLabelGenerator(StoragePlaceSO::getDescription);
		comboBoxStoragePlace.setLabel("Storage Place");
		comboBoxStoragePlace.setSizeFull();
		textFieldId.setEnabled(false);
		textFieldId.setValue("0");
		textFieldId.setSizeFull();
		add( //
				this.textFieldId, //
				this.comboBoxItem, //
				this.comboBoxStoragePlace, //
				this.datePickerBestBeforeDate, //
				this.buttonSave, //
				this.buttonDelete, //
				this.gridProducts //
		);
		try {
			updateView();
		} catch (PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
	}

	public void updateView() throws PersistenceException {
		List<ProductSO> products = this.productService.findAll().getResults() //
				.stream() //
				.sorted((product0, product1) -> product0.getItem().getDescription()
						.compareToIgnoreCase(product1.getItem().getDescription())) //
				.collect(Collectors.toList()) //
		;
		this.gridProducts.setItems(products);
		double meals = products.stream().mapToDouble(product -> product.getItem().getMeals()).sum();
		this.gridProducts.setItems(products);
		mealsColumn.setHeader("Meals (" + meals + ")");
		this.comboBoxItem.setItems(getItems());
		this.comboBoxStoragePlace.setItems(getStoragePlaces());
	}

	private void deleteItem(Set<ProductSO> products) {
		products.forEach(product -> {
			try {
				this.productService.delete(product.getId());
				updateView();
				cleanInput();
				this.eventProvider.fireEvent(new Event(EventType.PRODUCT_UPDATE, product.getId()));
			} catch (PersistenceException pe) {
				showError(pe.getMessage());
			}
		});
	}

	private void cleanInput() {
		textFieldId.setValue("0");
		comboBoxItem.clear();
		comboBoxStoragePlace.clear();
		datePickerBestBeforeDate.setValue(LocalDate.now());
	}

	private void saveItem(ItemSO item, StoragePlaceSO storagePlace, LocalDate bestBeforeDate, String id) {
		try {
			this.productService.save(new ProductSO() //
					.setId(Long.parseLong(id)) //
					.setBestBeforeDate(bestBeforeDate) //
					.setItem(item) //
					.setStoragePlace(storagePlace));
			this.eventProvider.fireEvent(new Event(EventType.PRODUCT_UPDATE, Long.parseLong(id)));
			updateView();
			cleanInput();
		} catch (PersistenceException pe) {
			showError(pe.getMessage());
		}
	}

	private void showError(String message) {
		new ConfirmDialog("Fehler", message, "Ok", event -> {
		}).open();
	}

	private void putToEditor(ProductSO product) {
		textFieldId.setValue("" + product.getId());
		comboBoxItem.setValue(product.getItem());
		comboBoxStoragePlace.setValue(product.getStoragePlace());
		datePickerBestBeforeDate.setValue(product.getBestBeforeDate());
	}

	@Override
	public void eventDetected(Event event) {
		this.comboBoxItem.setItems(getItems());
		this.comboBoxStoragePlace.setItems(getStoragePlaces());
	}

	private List<ItemSO> getItems() {
		try {
			return this.itemService.findAll().getResults() //
					.stream() //
					.sorted((item0, item1) -> item0.getDescription().compareToIgnoreCase(item1.getDescription())) //
					.collect(Collectors.toList()) //
			;
		} catch (PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
		return new ArrayList<>();
	}

	private List<StoragePlaceSO> getStoragePlaces() {
		try {
			return this.storagePlaceService.findAll().getResults() //
					.stream() //
					.sorted((storagePlace0, storagePlace1) -> storagePlace0.getDescription()
							.compareToIgnoreCase(storagePlace1.getDescription())) //
					.collect(Collectors.toList()) //
			;
		} catch (PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
		return new ArrayList<>();
	}

}