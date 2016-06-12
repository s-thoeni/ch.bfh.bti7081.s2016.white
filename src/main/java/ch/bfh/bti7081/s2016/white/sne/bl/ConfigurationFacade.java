package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public interface ConfigurationFacade {	
	public Configuration getConfig(User user) throws SneException;
	
	public void setConfig(List<ReportConfig> reports, User user)  throws SneException;
}
