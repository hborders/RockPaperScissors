package com.github.hborders.rockpaperscissors;

public class BestofGameFactory implements IGameFactory {
	private final int gameCount;
	private final BestofGame.Provider bestofGameProvider;
	private final Round.Provider roundProvider;
	private final AttemptReader attemptReader;

	public BestofGameFactory(int gameCount) throws InvalidGameCountException {
		this(gameCount, new BestofGame.Provider(), new Round.Provider(),
				new AttemptReader());
	}

	BestofGameFactory(int gameCount, BestofGame.Provider bestofGameProvider,
			Round.Provider roundProvider, AttemptReader attemptReader)
			throws InvalidGameCountException {
		super();
		if ((gameCount % 2) == 0) {
			throw new InvalidGameCountException();
		}
		this.gameCount = gameCount;
		this.bestofGameProvider = bestofGameProvider;
		this.roundProvider = roundProvider;
		this.attemptReader = attemptReader;
	}

	@Override
	public BestofGame createGame(Player firstPlayer, Player secondPlayer) {
		return bestofGameProvider.provide(gameCount, firstPlayer, secondPlayer,
				roundProvider, attemptReader);
	}

	public static class InvalidGameCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class Provider {
		BestofGameFactory provide(int gameCount,
				BestofGame.Provider bestofGameProvider,
				Round.Provider roundProvider, AttemptReader attemptReader)
				throws InvalidGameCountException {
			return new BestofGameFactory(gameCount, bestofGameProvider,
					roundProvider, attemptReader);
		}
	}
}
