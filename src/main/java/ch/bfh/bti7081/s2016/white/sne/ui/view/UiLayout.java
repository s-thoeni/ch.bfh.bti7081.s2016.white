package ch.bfh.bti7081.s2016.white.sne.ui.view;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;

public interface UiLayout {
	
	public interface UiLayoutListener {
		void menuOpened();
		void menuClosed();
		
		void showDashboard(Configuration config);
		void showConfiguration();
		void showReport();
	}
	
	public void addListener(UiLayoutListener listener);
}
