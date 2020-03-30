package de.ollie.homstorm.gui;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.gui.events.EventType;
import de.ollie.homstorm.service.ItemService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.ItemSO;

/**
 * A view for item maintenance.
 *
 * @author ollie (19.03.2020)
 */
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class ItemView extends VerticalLayout implements UpdatableView {

	private final ItemService itemService;
	private final EventProvider eventProvider;

	private Button buttonDelete = new Button("Delete");
	private Button buttonSave = new Button("Save");
	private Column<ItemSO> mealsColumn = null;
	private Grid<ItemSO> gridItems = new Grid<>(10);
	private NumberField numberFieldMeals = new NumberField("Meals");
	private NumberField numberFieldMessageDaysBefore = new NumberField("Message Days Before");
	private TextField textFieldId = new TextField("Id");
	private TextField textFieldDescription = new TextField("Description");

	public ItemView(EventProvider eventProvider, ItemService itemService) {
		super();
		this.eventProvider = eventProvider;
		this.itemService = itemService;
		getStyle().set("border", "1px solid LightGray");
		buttonDelete.addClickListener(event -> deleteItem(gridItems.getSelectedItems()));
		buttonDelete.setSizeFull();
		buttonSave.addClickListener(event -> saveItem(textFieldDescription.getValue(), textFieldId.getValue()));
		buttonSave.setSizeFull();
		gridItems.addColumn(ItemSO::getDescription).setHeader("Item");
		mealsColumn = gridItems.addColumn(ItemSO::getMeals).setHeader("Meals");
		gridItems.addItemDoubleClickListener(event -> putToEditor(event.getItem()));
		gridItems.setWidthFull();
		numberFieldMessageDaysBefore.setSizeFull();
		numberFieldMessageDaysBefore.setMin(0);
		numberFieldMessageDaysBefore.setStep(1.0D);
		numberFieldMessageDaysBefore.setValue(7.0D);
		textFieldDescription.setSizeFull();
		textFieldId.setEnabled(false);
		textFieldId.setValue("0");
		textFieldId.setSizeFull();
		add( //
				this.textFieldId, //
				this.textFieldDescription, //
				this.numberFieldMessageDaysBefore, //
				this.numberFieldMeals, //
				this.buttonSave, //
				this.buttonDelete, //
				this.gridItems //
		);
		try {
			updateView();
		} catch (PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
	}

	public void updateView() throws PersistenceException {
		List<ItemSO> items = this.itemService.findAll().getResults() //
				.stream() //
				.sorted((item0, item1) -> item0.getDescription().compareToIgnoreCase(item1.getDescription())) //
				.collect(Collectors.toList()) //
		;
		this.gridItems.setItems(items);
	}

	private void deleteItem(Set<ItemSO> items) {
		items.forEach(item -> {
			try {
				this.itemService.delete(item.getId());
				updateView();
				cleanInput();
				this.eventProvider.fireEvent(new Event(EventType.ITEM_UPDATE, item.getId()));
			} catch (PersistenceException pe) {
				showError(pe.getMessage());
			}
		});
	}

	private void cleanInput() {
		numberFieldMeals.setValue(1.0D);
		numberFieldMessageDaysBefore.setValue(7.0D);
		textFieldDescription.setValue("");
		textFieldId.setValue("0");
	}

	private void saveItem(String description, String id) {
		try {
			this.itemService.save(new ItemSO() //
					.setId(Long.parseLong(id)) //
					.setDescription(description) //
					.setMeals(numberFieldMeals.getValue()) //
					.setMessageDaysBeforeBestBeforeDate(numberFieldMessageDaysBefore.getValue().intValue()));
			updateView();
			cleanInput();
			this.eventProvider.fireEvent(new Event(EventType.ITEM_UPDATE, Long.parseLong(id)));
		} catch (PersistenceException pe) {
			showError(pe.getMessage());
		}
	}

	private void showError(String message) {
		new ConfirmDialog("Fehler", message, "Ok", event -> {
		}).open();
	}

	private void putToEditor(ItemSO item) {
		numberFieldMeals.setValue(item.getMeals());
		numberFieldMessageDaysBefore.setValue(item.getMessageDaysBeforeBestBeforeDate().doubleValue());
		textFieldDescription.setValue(item.getDescription());
		textFieldId.setValue("" + item.getId());
	}

}