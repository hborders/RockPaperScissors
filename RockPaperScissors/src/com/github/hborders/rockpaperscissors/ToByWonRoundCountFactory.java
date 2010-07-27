package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.WonRoundCount.Provider;
import com.google.inject.Inject;

public class ToByWonRoundCountFactory {
	private final WonRoundCount.Provider wonRoundCountProvider;

	@Inject
	public ToByWonRoundCountFactory(Provider wonRoundCountProvider) {
		this.wonRoundCountProvider = wonRoundCountProvider;
	}

	public WonRoundCount createWinningWonRoundCount(RoundCount toRoundCount,
			RoundCount byRoundCount) {
		if (toRoundCount.getRawRoundCount() < byRoundCount.getRawRoundCount()) {
			return wonRoundCountProvider.provide(byRoundCount
					.getRawRoundCount());
		} else {
			return wonRoundCountProvider.provide(toRoundCount
					.getRawRoundCount());
		}
	}

	public WonRoundCount createExtendingWonRoundCount(RoundCount toRoundCount,
			RoundCount byRoundCount) {
		if (toRoundCount.getRawRoundCount() < byRoundCount.getRawRoundCount()) {
			return wonRoundCountProvider.provide(0);
		} else {
			return wonRoundCountProvider.provide(toRoundCount
					.getRawRoundCount()
					- byRoundCount.getRawRoundCount());
		}
	}
}
