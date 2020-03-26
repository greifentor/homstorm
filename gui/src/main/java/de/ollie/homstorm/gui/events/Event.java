package de.ollie.homstorm.gui.events;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A container for the event data.
 *
 * @author ollie (26.03.2020)
 */
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Event {

	private EventType type;
	private long id;

}