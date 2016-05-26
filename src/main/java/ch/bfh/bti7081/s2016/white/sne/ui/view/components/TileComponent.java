package ch.bfh.bti7081.s2016.white.sne.ui.view.components;

import java.util.Date;

public interface TileComponent {
	
    public void setTitle(String title);    
    
    public void setValue(String value);    

    interface TileClickListener {
        void tileClick(String title);
    }
    
    public void addListener(TileClickListener listener);

	void setTimeframe(Date from, Date to);    
}