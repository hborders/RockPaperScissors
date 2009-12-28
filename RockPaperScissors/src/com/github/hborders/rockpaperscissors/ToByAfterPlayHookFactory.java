package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHookFactory;

public class ToByAfterPlayHookFactory implements IAfterPlayHookFactory {
	private final ToByAfterPlayHook.Provider toByAfterPlayHookProvider;
	private final WonRoundCount extendingWonRoundCount;
	private final WonRoundCount winningWonRoundCount;

	ToByAfterPlayHookFactory(
			ToByAfterPlayHook.Provider toByAfterPlayHookProvider,
			WonRoundCount extendingWonRoundCount,
			WonRoundCount winningWonRoundCount) {
		this.toByAfterPlayHookProvider = toByAfterPlayHookProvider;
		this.extendingWonRoundCount = extendingWonRoundCount;
		this.winningWonRoundCount = winningWonRoundCount;
	}

	@Override
	public ToByAfterPlayHook createAfterPlayHook(Player firstPlayer,
			Player secondPlayer) {
		return toByAfterPlayHookProvider.provide(extendingWonRoundCount,
				winningWonRoundCount, firstPlayer, secondPlayer);
	}

	static class Provider {
		public ToByAfterPlayHookFactory provide(
				ToByAfterPlayHook.Provider toByAfterPlayHookProvider,
				WonRoundCount extendingWonRoundCount,
				WonRoundCount winningWonRoundCount) {
			return new ToByAfterPlayHookFactory(toByAfterPlayHookProvider,
					extendingWonRoundCount, winningWonRoundCount);
		}
	}
}
