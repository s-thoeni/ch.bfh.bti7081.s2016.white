package ch.bfh.bti7081.s2016.white.sne.data.states;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;

public interface AlarmState {
	public Object[] visualizeAlarm(Alarm alarm);
}
