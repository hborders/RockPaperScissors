package com.github.hborders.rockpaperscissors;

public class WonRoundCount {
	private int innerWonRoundCount;

	public WonRoundCount() {
		this(0);
	}

	WonRoundCount(int innerWonRoundCount) {
		this.innerWonRoundCount = innerWonRoundCount;
	}

	public void increment() {
		innerWonRoundCount++;
	}

	public boolean matches(WonRoundCount wonRoundCount) {
		return innerWonRoundCount == wonRoundCount.innerWonRoundCount;
	}

	static class Provider {
		public WonRoundCount provide(int wonRoundCount) {
			return new WonRoundCount(wonRoundCount);
		}
	}
}
