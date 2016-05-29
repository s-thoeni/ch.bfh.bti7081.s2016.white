package ch.bfh.bti7081.s2016.white.sne.ui.view;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;

public interface SneMenuView {
	
	public interface SneMenuListener {
		void menuOpened();
		void menuClosed();
		
		void showDashboard(Configuration config);
		void showConfiguration();
		void showReports();
	}
	
	public void addListener(SneMenuListener listener);
}
