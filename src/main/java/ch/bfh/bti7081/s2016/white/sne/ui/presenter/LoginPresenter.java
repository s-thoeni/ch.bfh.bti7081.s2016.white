package ch.bfh.bti7081.s2016.white.sne.ui.presenter;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.vaadin.ui.Component;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import ch.bfh.bti7081.s2016.white.sne.MyUI;
import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.AlarmFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacade;
import ch.bfh.bti7081.s2016.white.sne.bl.ConfigurationFacadeImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;
import ch.bfh.bti7081.s2016.white.sne.ui.model.DashboardProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.model.LoginProvider;
import ch.bfh.bti7081.s2016.white.sne.ui.view.DashboardViewImpl;
import ch.bfh.bti7081.s2016.white.sne.ui.view.LoginView;
import ch.bfh.bti7081.s2016.white.sne.ui.view.LoginViewImpl;

public class LoginPresenter implements LoginView.LoginViewListener {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LogManager.getLogger(LoginPresenter.class);
	
	private LoginProvider model;
	private LoginViewImpl view;
	private static boolean securityEnabled = false;

	public LoginPresenter(LoginProvider model, LoginViewImpl view) {
		this.model = model;
		this.view = view;

		view.addListener(this);
	}

	@Override
	public void loginClick() throws NoSuchAlgorithmException {
		logger.debug("->");
		User loginUser = view.getLoginName();
		String password = view.getPassword();

		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

		String pwEntered = DatatypeConverter.printHexBinary(hash);

		
		if (securityEnabled) {
			logger.debug("Security enabled");
			
			System.out.println("hasehd: " + pwEntered);
			System.out.println(loginUser.getPassword());
			if (pwEntered.equals(loginUser.getPassword().toUpperCase())) {
				ConfigurationFacade configFac;
				try {
					configFac = new ConfigurationFacadeImpl();

					Configuration config = configFac.getConfig(loginUser);

					AlarmFacade alarmFac = new AlarmFacadeImpl(loginUser);
					List<Alarm> alarms = alarmFac.getAlarms();

					DashboardProvider provider = new DashboardProvider(config, loginUser);
					DashboardViewImpl view = new DashboardViewImpl(config, loginUser, alarms);

					DashboardPresenter db = new DashboardPresenter(provider, view);

					MyUI ui = (MyUI) UI.getCurrent();
					if (ui instanceof MyUI) {
						MyUI sneui = (MyUI) ui;
						sneui.setDashboard(db);
					}

					ui.getNavigationManager().navigateTo(db.getView());
				} catch (SneException e) {
					Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
				}
			}
		} else {
			logger.debug("Security disabled");
			
			ConfigurationFacade configFac;
			try {
				configFac = new ConfigurationFacadeImpl();

				Configuration config = configFac.getConfig(loginUser);

				AlarmFacade alarmFac = new AlarmFacadeImpl(loginUser);
				List<Alarm> alarms = alarmFac.getAlarms();

				DashboardProvider provider = new DashboardProvider(config, loginUser);
				DashboardViewImpl view = new DashboardViewImpl(config, loginUser, alarms);

				DashboardPresenter db = new DashboardPresenter(provider, view);

				MyUI ui = (MyUI) UI.getCurrent();
				if (ui instanceof MyUI) {
					MyUI sneui = (MyUI) ui;
					sneui.setDashboard(db);
				}

				ui.getNavigationManager().navigateTo(db.getView());
			} catch (SneException e) {
				Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
			}
		}
		logger.debug("<-");
	}

	public Component getView() {
		logger.debug("->");
		logger.debug("<-");
		return this.view;
	}

}
