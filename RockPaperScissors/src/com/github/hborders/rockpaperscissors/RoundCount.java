package com.github.hborders.rockpaperscissors;

public class RoundCount {
	private final int rawRoundCount;

	public RoundCount(int roundCount) {
		this.rawRoundCount = roundCount;
	}

	public int getRawRoundCount() {
		return rawRoundCount;
	}

	@Override
	public String toString() {
		return "RoundCount[" + rawRoundCount + "]";
	}
}
