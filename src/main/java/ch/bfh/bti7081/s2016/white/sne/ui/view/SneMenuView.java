package ch.bfh.bti7081.s2016.white.sne.ui.view;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public interface SneMenuView {
	
	public interface SneMenuListener {
		void menuOpened();
		void menuClosed();
		
		void showDashboard(Configuration config);

		void showConfiguration() throws SneException;
		void showReports();

		void showAlarms() throws SneException;
	}
	
	public void addListener(SneMenuListener listener);
}
