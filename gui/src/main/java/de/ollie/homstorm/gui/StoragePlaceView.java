package de.ollie.homstorm.gui;

import java.util.Set;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.gui.events.EventType;
import de.ollie.homstorm.service.StoragePlaceService;
import de.ollie.homstorm.service.persistence.exception.PersistenceException;
import de.ollie.homstorm.service.so.StoragePlaceSO;

/**
 * A view for item maintenance.
 *
 * @author ollie (19.03.2020)
 */
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class StoragePlaceView extends VerticalLayout {

	private final StoragePlaceService storagePlaceService;
	private final EventProvider eventProvider;

	private Button buttonDelete = new Button("Delete");
	private Button buttonSave = new Button("Save");
	private Grid<StoragePlaceSO> gridStoragePlaces = new Grid<>(10);
	private TextField textFieldId = new TextField("Id");
	private TextField textFieldDescription = new TextField("Description");

	public StoragePlaceView(EventProvider eventProvider, StoragePlaceService storagePlaceService) {
		super();
		this.eventProvider = eventProvider;
		this.storagePlaceService = storagePlaceService;
		getStyle().set("border", "1px solid LightGray");
		buttonDelete.addClickListener(event -> deleteItem(gridStoragePlaces.getSelectedItems()));
		buttonDelete.setSizeFull();
		buttonSave.addClickListener(event -> saveItem(textFieldDescription.getValue(), textFieldId.getValue()));
		buttonSave.setSizeFull();
		gridStoragePlaces.addColumn(item -> item.getDescription()).setHeader("Item");
		gridStoragePlaces.addItemDoubleClickListener(event -> putToEditor(event.getItem()));
		gridStoragePlaces.setWidthFull();
		textFieldDescription.setSizeFull();
		textFieldId.setEnabled(false);
		textFieldId.setValue("0");
		textFieldId.setSizeFull();
		add( //
				this.textFieldId, //
				this.textFieldDescription, //
				this.buttonSave, //
				this.buttonDelete, //
				this.gridStoragePlaces //
		);
		try {
			updateGrid();
		} catch (PersistenceException pe) {
			System.out.println(pe.getMessage());
		}
	}

	private void updateGrid() throws PersistenceException {
		this.gridStoragePlaces.setItems( //
				this.storagePlaceService.findAll().getResults() //
						.stream() //
						.sorted((storagePlace0, storagePlace1) -> storagePlace0.getDescription()
								.compareToIgnoreCase(storagePlace1.getDescription())) //
		);
	}

	private void deleteItem(Set<StoragePlaceSO> storagePlaces) {
		storagePlaces.forEach(storagePlace -> {
			try {
				this.storagePlaceService.delete(storagePlace.getId());
				updateGrid();
				cleanInput();
				this.eventProvider.fireEvent(new Event(EventType.STORAGE_PLACE_UPDATE, storagePlace.getId()));
			} catch (PersistenceException pe) {
				showError(pe.getMessage());
			}
		});
	}

	private void cleanInput() {
		textFieldDescription.setValue("");
		textFieldId.setValue("0");
	}

	private void saveItem(String description, String id) {
		try {
			this.storagePlaceService.save(new StoragePlaceSO().setId(Integer.parseInt(id)).setDescription(description));
			updateGrid();
			cleanInput();
			this.eventProvider.fireEvent(new Event(EventType.STORAGE_PLACE_UPDATE, Long.parseLong(id)));
		} catch (PersistenceException pe) {
			showError(pe.getMessage());
		}
	}

	private void showError(String message) {
		new ConfirmDialog("Fehler", message, "Ok", event -> {
		}).open();
	}

	private void putToEditor(StoragePlaceSO item) {
		textFieldDescription.setValue(item.getDescription());
		textFieldId.setValue("" + item.getId());
	}

}