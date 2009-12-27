package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.WonRoundCount.Provider;

public class ToByWonRoundCountFactory {
	private final WonRoundCount.Provider wonRoundCountProvider;

	ToByWonRoundCountFactory(Provider wonRoundCountProvider) {
		this.wonRoundCountProvider = wonRoundCountProvider;
	}

	public WonRoundCount createWonRoundCount(RoundCount toRoundCount,
			RoundCount byRoundCount) {
		if (toRoundCount.getRawRoundCount() < byRoundCount.getRawRoundCount()) {
			return wonRoundCountProvider.provide(byRoundCount
					.getRawRoundCount());
		} else {
			return wonRoundCountProvider.provide(toRoundCount
					.getRawRoundCount());
		}
	}
}
