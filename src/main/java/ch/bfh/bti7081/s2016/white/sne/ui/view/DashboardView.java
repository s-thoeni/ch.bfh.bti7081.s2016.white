package ch.bfh.bti7081.s2016.white.sne.ui.view;

import com.vaadin.ui.Component;

import ch.bfh.bti7081.s2016.white.sne.ui.view.components.TileComponentImpl;

public interface DashboardView {	   

    public interface DashboardViewListener {
        void tileClick(String id);
    }
    
    public void addListener(DashboardViewListener listener);

	/**
	 * Add a TileComponent and register listener
	 * @param tile
	 */
	void addTile(TileComponentImpl tile);

	public void navigateTo(Component component);    
}