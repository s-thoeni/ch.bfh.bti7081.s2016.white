package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

public interface AlarmSet {
	
	public interface AlarmSetListener {
		void deleteClick(String string);
	}
	
	public void addListener(AlarmSetListener listener);
}