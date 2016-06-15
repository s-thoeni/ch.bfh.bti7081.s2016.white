package ch.bfh.bti7081.s2016.white.sne.ui.view;

import java.security.NoSuchAlgorithmException;
 /**
  *
  * @author shepd1
  *
  */
public interface LoginView {
	
	public interface LoginViewListener {
		void loginClick() throws NoSuchAlgorithmException;
	}
	
	public void addListener(LoginViewListener listener) ;
	
}