package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.dao.ConfigurationDAO;
import ch.bfh.bti7081.s2016.white.sne.dao.ConfigurationDAOImpl;
import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public class ConfigurationFacadeImpl implements ConfigurationFacade{
	private ConfigurationDAO dao = new ConfigurationDAOImpl();
	
	@Override
	public Configuration getConfig(User user) {
		return dao.getConfig(user);
	}

	@Override
	public void setConfig(List<ReportConfig> reports, User user) {
		dao.setConfig(reports, user);
	}

}
