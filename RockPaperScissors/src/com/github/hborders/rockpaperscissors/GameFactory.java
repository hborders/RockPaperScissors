package com.github.hborders.rockpaperscissors;

public class GameFactory {
	protected final WonRoundCount winningWonRoundCount;
	protected final Round round;
	protected final Game.Provider gameProvider;

	public GameFactory(WonRoundCount winningWonRoundCount, Round round) {
		this(winningWonRoundCount, round, new Game.Provider());
	}

	GameFactory(WonRoundCount winningWonRoundCount, Round round,
			Game.Provider gameProvider) {
		this.winningWonRoundCount = winningWonRoundCount;
		this.round = round;
		this.gameProvider = gameProvider;
	}

	public Game createGame(Player firstPlayer, Player secondPlayer) {
		return gameProvider.provide(winningWonRoundCount, firstPlayer,
				secondPlayer, round);
	}

	static class Provider {
		public GameFactory provide(WonRoundCount winningWonRoundCount,
				Round round, Game.Provider gameProvider) {
			return new GameFactory(winningWonRoundCount, round, gameProvider);
		}
	}
}
