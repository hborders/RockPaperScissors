package com.github.hborders.rockpaperscissors;

public class BestofGameFactory implements IGameFactory {
	private final int gameCount;
	private final BestofGame.Provider bestofGameProvider;
	private final Round round;

	public BestofGameFactory(int gameCount) throws InvalidGameCountException {
		this(gameCount, new BestofGame.Provider(), new Round());
	}

	BestofGameFactory(int gameCount, BestofGame.Provider bestofGameProvider,
			Round round) throws InvalidGameCountException {
		super();
		if ((gameCount % 2) == 0) {
			throw new InvalidGameCountException();
		}
		this.gameCount = gameCount;
		this.bestofGameProvider = bestofGameProvider;
		this.round = round;
	}

	@Override
	public BestofGame createGame(Player firstPlayer, Player secondPlayer) {
		return bestofGameProvider.provide(gameCount, firstPlayer, secondPlayer,
				round);
	}

	public static class InvalidGameCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class Provider {
		BestofGameFactory provide(int gameCount,
				BestofGame.Provider bestofGameProvider, Round round)
				throws InvalidGameCountException {
			return new BestofGameFactory(gameCount, bestofGameProvider, round);
		}
	}
}
