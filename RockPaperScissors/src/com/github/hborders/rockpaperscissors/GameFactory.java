package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;
import com.github.hborders.rockpaperscissors.Round.IAfterPlayHookFactory;

public class GameFactory {
	private final WonRoundCount winningWonRoundCount;
	private final IAfterPlayHookFactory afterPlayHookFactory;
	private final AttemptReader attemptReader;
	private final Round.Provider roundProvider;
	private final Game.Provider gameProvider;

	GameFactory(WonRoundCount winningWonRoundCount,
			IAfterPlayHookFactory afterPlayHookFactory,
			AttemptReader attemptReader, Round.Provider roundProvider,
			Game.Provider gameProvider) {
		this.winningWonRoundCount = winningWonRoundCount;
		this.afterPlayHookFactory = afterPlayHookFactory;
		this.attemptReader = attemptReader;
		this.roundProvider = roundProvider;
		this.gameProvider = gameProvider;
	}

	public Game createGame(Player firstPlayer, Player secondPlayer) {
		IAfterPlayHook afterPlayHook = afterPlayHookFactory
				.createAfterPlayHook(firstPlayer, secondPlayer);
		Round round = roundProvider.provide(attemptReader, afterPlayHook);
		return gameProvider.provide(winningWonRoundCount, firstPlayer,
				secondPlayer, round);
	}

	public static class Provider {
		public GameFactory provide(WonRoundCount winningWonRoundCount,
				IAfterPlayHookFactory afterPlayHookFactory,
				AttemptReader attemptReader, Round.Provider roundProvider,
				Game.Provider gameProvider) {
			return new GameFactory(winningWonRoundCount, afterPlayHookFactory,
					attemptReader, roundProvider, gameProvider);
		}
	}
}
