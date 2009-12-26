package com.github.hborders.rockpaperscissors;

public class DefaultGameFactory implements IGameFactory {
	private final Round round;
	private final WonRoundCount.Provider wonRoundCountProvider;
	private final Game.Provider gameProvider;

	public DefaultGameFactory() {
		this(new Round(), new WonRoundCount.Provider(), new Game.Provider());
	}

	DefaultGameFactory(Round round,
			WonRoundCount.Provider wonRoundCountProvider,
			Game.Provider gameProvider) {
		this.round = round;
		this.wonRoundCountProvider = wonRoundCountProvider;
		this.gameProvider = gameProvider;
	}

	@Override
	public Game createGame(Player firstPlayer, Player secondPlayer) {
		WonRoundCount winningWonRoundCount = wonRoundCountProvider.provide(1);
		return gameProvider.provide(winningWonRoundCount, firstPlayer,
				secondPlayer, round);
	}

	static class Provider {
		public DefaultGameFactory provide(Round round,
				WonRoundCount.Provider wonRoundCountProvider,
				Game.Provider gameProvider) {
			return new DefaultGameFactory(round, wonRoundCountProvider,
					gameProvider);
		}
	}
}
