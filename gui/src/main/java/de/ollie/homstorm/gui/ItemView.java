package de.ollie.homstorm.gui;

import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
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
public class ItemView extends VerticalLayout {

	private final ItemService itemService;
	private final EventProvider eventProvider;

	private Button buttonDelete = new Button("Delete");
	private Button buttonSave = new Button("Save");
	private Grid<ItemSO> gridItems = new Grid<>(10);
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
		gridItems.addColumn(item -> item.getDescription()).setHeader("Item");
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
		addClassName("centered-content");
		add( //
				this.textFieldId, //
				this.textFieldDescription, //
				this.numberFieldMessageDaysBefore, //
				this.buttonSave, //
				this.buttonDelete, //
				this.gridItems //
		);
		try {
			updateGrid();
		} catch (PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
	}

	private void updateGrid() throws PersistenceException {
		this.gridItems.setItems(this.itemService.findAll().getResults());
	}

	private void deleteItem(Set<ItemSO> items) {
		items.forEach(item -> {
			try {
				this.itemService.delete(item.getId());
				updateGrid();
				cleanInput();
				this.eventProvider.fireEvent(new Event(EventType.ITEM_UPDATE, item.getId()));
			} catch (PersistenceException pe) {
				showError(pe.getMessage());
			}
		});
	}

	private void cleanInput() {
		numberFieldMessageDaysBefore.setValue(7.0D);
		textFieldDescription.setValue("");
		textFieldId.setValue("0");
	}

	private void saveItem(String description, String id) {
		try {
			this.itemService.save(new ItemSO() //
					.setId(Long.parseLong(id)) //
					.setDescription(description)
					.setMessageDaysBeforeBestBeforeDate(numberFieldMessageDaysBefore.getValue().intValue()));
			updateGrid();
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
		numberFieldMessageDaysBefore.setValue(item.getMessageDaysBeforeBestBeforeDate().doubleValue());
		textFieldDescription.setValue(item.getDescription());
		textFieldId.setValue("" + item.getId());
	}

}