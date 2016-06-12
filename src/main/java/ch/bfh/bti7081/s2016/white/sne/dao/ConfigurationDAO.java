package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Configuration;
import ch.bfh.bti7081.s2016.white.sne.data.ReportConfig;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public interface ConfigurationDAO {
	public Configuration getConfig() throws SneException;

	public Configuration getConfig(User user) throws SneException;

	public void setConfig(List<ReportConfig> reports, User user) throws SneException;
}
