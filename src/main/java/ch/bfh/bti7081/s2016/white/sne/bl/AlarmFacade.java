package ch.bfh.bti7081.s2016.white.sne.bl;

import java.util.List;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;
import ch.bfh.bti7081.s2016.white.sne.data.User;

public interface AlarmFacade {
	public List<Alarm> getAlarms(User user);
}
