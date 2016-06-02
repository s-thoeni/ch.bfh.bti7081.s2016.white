package ch.bfh.bti7081.s2016.white.sne.data.states;

import ch.bfh.bti7081.s2016.white.sne.data.Alarm;

public class AlarmNotCheckedState implements AlarmState {

	@Override
	public Object[] visualizeAlarm(Alarm alarm) {
		// no output when not checked
		return null;
	}

}
