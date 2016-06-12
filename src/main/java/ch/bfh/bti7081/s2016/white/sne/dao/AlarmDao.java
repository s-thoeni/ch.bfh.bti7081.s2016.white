package ch.bfh.bti7081.s2016.white.sne.dao;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;
import ch.bfh.bti7081.s2016.white.sne.data.exceptions.SneException;

public interface AlarmDao {
	public List<Alarm> getAlarms(User user) throws SneException;

	void storeAlarms(List<Alarm> alarms, User user) throws SneException;
}