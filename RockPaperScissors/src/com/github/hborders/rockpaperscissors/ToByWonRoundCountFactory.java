package com.github.hborders.rockpaperscissors;

public class ToByWonRoundCountFactory {
	public WonRoundCount createWonRoundCount(RoundCount toRoundCount,
			RoundCount byRoundCount) {
		return new WonRoundCount(toRoundCount.getRoundCount()
				+ byRoundCount.getRoundCount() - 1);
	}
}
