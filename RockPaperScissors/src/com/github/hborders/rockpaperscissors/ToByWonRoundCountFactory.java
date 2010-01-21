package com.github.hborders.rockpaperscissors;


public class ToByWonRoundCountFactory {

	public WonRoundCount createWinningWonRoundCount(RoundCount toRoundCount,
			RoundCount byRoundCount) {
		if (toRoundCount.getRawRoundCount() < byRoundCount.getRawRoundCount()) {
			return new WonRoundCount(byRoundCount.getRawRoundCount());
		} else {
			return new WonRoundCount(toRoundCount.getRawRoundCount());
		}
	}

	public WonRoundCount createExtendingWonRoundCount(RoundCount toRoundCount,
			RoundCount byRoundCount) {
		if (toRoundCount.getRawRoundCount() < byRoundCount.getRawRoundCount()) {
			return new WonRoundCount(0);
		} else {
			return new WonRoundCount(toRoundCount.getRawRoundCount()
					- byRoundCount.getRawRoundCount());
		}
	}
}
