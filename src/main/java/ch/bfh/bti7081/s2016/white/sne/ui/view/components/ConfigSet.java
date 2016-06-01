package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

public interface ConfigSet {
	
	public interface ConfigSetListener {
		void deleteClick(String string);

	}
	
	public void addListener(ConfigSetListener listener);

}
