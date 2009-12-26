package com.github.hborders.rockpaperscissors;

public class WonRoundCount {
	private int rawWonRoundCount;

	WonRoundCount(int innerWonRoundCount) {
		this.rawWonRoundCount = innerWonRoundCount;
	}

	public void increment() {
		rawWonRoundCount++;
	}

	public boolean matches(WonRoundCount wonRoundCount) {
		return rawWonRoundCount == wonRoundCount.rawWonRoundCount;
	}

	@Override
	public String toString() {
		return "WonRoundCount[" + rawWonRoundCount + "]";
	}

	static class Provider {
		public WonRoundCount provide(int wonRoundCount) {
			return new WonRoundCount(wonRoundCount);
		}
	}
}
