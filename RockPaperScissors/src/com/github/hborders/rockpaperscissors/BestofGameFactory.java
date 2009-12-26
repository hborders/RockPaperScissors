package com.github.hborders.rockpaperscissors;

public class BestofGameFactory implements IGameFactory {
	private final Game.Provider gameProvider;
	private final WonRoundCount winningWonRoundCount;
	private final Round round;

	public BestofGameFactory(WonRoundCount winningWonRoundCount) {
		this(new Game.Provider(), winningWonRoundCount, new Round());
	}

	BestofGameFactory(Game.Provider gameProvider,
			WonRoundCount winningWonRoundCount, Round round) {
		this.gameProvider = gameProvider;
		this.winningWonRoundCount = winningWonRoundCount;
		this.round = round;
	}

	@Override
	public Game createGame(Player firstPlayer, Player secondPlayer) {
		return gameProvider.provide(winningWonRoundCount, firstPlayer,
				secondPlayer, round);
	}

	static class Provider {
		BestofGameFactory provide(Game.Provider gameProvider,
				WonRoundCount winningWonRoundCount, Round round) {
			return new BestofGameFactory(gameProvider,
					winningWonRoundCount, round);
		}
	}
}
