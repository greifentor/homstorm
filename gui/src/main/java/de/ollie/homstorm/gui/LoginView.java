package de.ollie.homstorm.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;

import de.ollie.homstorm.gui.events.Event;
import de.ollie.homstorm.gui.events.EventProvider;
import de.ollie.homstorm.gui.events.EventType;
import de.ollie.homstorm.service.UserService;

/**
 * A view for product maintenance.
 *
 * @author ollie (26.03.2020)
 */
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class LoginView extends VerticalLayout {

	private final EventProvider eventProvider;
	private final UserService userService;

	private Button buttonLogin = new Button("Login");
	private PasswordField passwordFieldPassword = new PasswordField("Password");

	public LoginView(EventProvider eventProvider, UserService userService) {
		super();
		this.eventProvider = eventProvider;
		this.userService = userService;
		getStyle().set("border", "1px solid LightGray");
		buttonLogin.addClickListener(event -> checkLogin("user", passwordFieldPassword.getValue()));
		buttonLogin.setSizeFull();
		passwordFieldPassword.focus();
		passwordFieldPassword.setSizeFull();
		add( //
				passwordFieldPassword, //
				buttonLogin //
		);
	}

	private void checkLogin(String userName, String password) {
		if (userService.isAccepted(userName, password)) {
			eventProvider.fireEvent(new Event(EventType.USER_ACCEPTED, -1));
		} else {
			passwordFieldPassword.setValue("");
			passwordFieldPassword.focus();
		}
	}

}