package com.aibinong.backyard.pojo;

public class TurnConf extends BaseDO implements Comparable<TurnConf> {
	
	private String clock;
	private int count;

	public String getClock() {
		return clock;
	}

	public void setClock(String clock) {
		this.clock = clock;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int compareTo(TurnConf o) {
		int x = Integer.parseInt(clock);
		int y = Integer.parseInt(o.getClock());
		if (x < y) {
			return -1;
		}
		if (x > y) {
			return 1;
		}
		return 0;
	}
}
