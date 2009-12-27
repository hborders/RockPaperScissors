package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHookFactory;

public class ToByAfterPlayHookFactory implements IAfterPlayHookFactory {
	private final ToByAfterPlayHook.Provider toByAfterPlayHookProvider;
	private final WonRoundCount winningWonRoundCount;

	ToByAfterPlayHookFactory(
			ToByAfterPlayHook.Provider toByAfterPlayHookProvider,
			WonRoundCount winningWonRoundCount) {
		this.toByAfterPlayHookProvider = toByAfterPlayHookProvider;
		this.winningWonRoundCount = winningWonRoundCount;
	}

	@Override
	public ToByAfterPlayHook createAfterPlayHook(Player firstPlayer,
			Player secondPlayer) {
		return toByAfterPlayHookProvider.provide(winningWonRoundCount,
				firstPlayer, secondPlayer);
	}

	static class Provider {
		public ToByAfterPlayHookFactory provide(
				ToByAfterPlayHook.Provider toByAfterPlayHookProvider,
				WonRoundCount winningWonRoundCount) {
			return new ToByAfterPlayHookFactory(toByAfterPlayHookProvider,
					winningWonRoundCount);
		}
	}
}
