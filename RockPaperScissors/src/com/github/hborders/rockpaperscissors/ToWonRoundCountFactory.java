package com.github.hborders.rockpaperscissors;

public class ToWonRoundCountFactory {
	public WonRoundCount createWonRoundCount(RoundCount toRoundCount) {
		return new WonRoundCount(toRoundCount.getRawRoundCount());
	}
}
