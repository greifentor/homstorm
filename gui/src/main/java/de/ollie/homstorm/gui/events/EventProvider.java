package de.ollie.homstorm.gui.events;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

/**
 * An event provider for
 *
 * @author ollie (26.03.2020)
 */
@Named
public class EventProvider {

	private List<EventListener> listeners = new ArrayList<>();

	public EventProvider() {
		super();
	}

	public void addListener(EventListener l) {
		if (l != null) {
			this.listeners.add(l);
		}
	}

	public void fireEvent(Event event) {
		new ArrayList<>(this.listeners).forEach(l -> {
			try {
				l.eventDetected(event);
			} catch (Exception e) {
				System.out.println("error while event fired: " + e.getMessage());
			}
		});
	}

	public void removeListener(EventListener l) {
		if (l != null) {
			this.listeners.remove(l);
		}
	}

}