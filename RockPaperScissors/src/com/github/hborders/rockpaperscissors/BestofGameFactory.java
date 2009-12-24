package com.github.hborders.rockpaperscissors;

public class BestofGameFactory implements IGameFactory {
	private final int gameCount;
	private final BestofGame.Provider bestofGameProvider;

	public BestofGameFactory(int gameCount) throws InvalidGameCountException {
		this(gameCount, new BestofGame.Provider());
	}

	BestofGameFactory(int gameCount, BestofGame.Provider bestofGameProvider)
			throws InvalidGameCountException {
		super();
		if ((gameCount % 2) == 0) {
			throw new InvalidGameCountException();
		}
		this.gameCount = gameCount;
		this.bestofGameProvider = bestofGameProvider;
	}

	@Override
	public IGame createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	public static class InvalidGameCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class Provider {
		BestofGameFactory provide(int gameCount,
				BestofGame.Provider bestofGameProvider)
				throws InvalidGameCountException {
			return new BestofGameFactory(gameCount, bestofGameProvider);
		}
	}
}
