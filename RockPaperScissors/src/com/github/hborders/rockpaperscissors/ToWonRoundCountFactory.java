package com.github.hborders.rockpaperscissors;

public class ToWonRoundCountFactory {
	public WonRoundCount createWinningWonRoundCount(RoundCount toRoundCount) {
		return new WonRoundCount(toRoundCount.getRawRoundCount());
	}
}
