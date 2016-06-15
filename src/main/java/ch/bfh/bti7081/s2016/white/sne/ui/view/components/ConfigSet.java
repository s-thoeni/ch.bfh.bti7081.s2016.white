package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

/**
 * A config set consists of a Report Type selector and a Timeframe selector and also a delete button.
 * This config set is used to add, edit and delete dashboard tiles.
 * 
 * @author shepd1
 *
 */
public interface ConfigSet {
	
	public interface ConfigSetListener {
		void deleteClick(String string);

	}
	
	public void addListener(ConfigSetListener listener);

}
