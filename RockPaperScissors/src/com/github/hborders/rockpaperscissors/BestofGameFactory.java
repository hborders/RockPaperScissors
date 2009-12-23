package com.github.hborders.rockpaperscissors;

public class BestofGameFactory implements IGameFactory {
	private final BestofGame.Provider bestofGameProvider;

	public BestofGameFactory() {
		this(new BestofGame.Provider());
	}

	BestofGameFactory(BestofGame.Provider bestofGameProvider) {
		super();
		this.bestofGameProvider = bestofGameProvider;
	}

	@Override
	public IGame createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		BestofGameFactory provide(BestofGame.Provider bestofGameProvider) {
			return new BestofGameFactory(bestofGameProvider);
		}
	}
}
