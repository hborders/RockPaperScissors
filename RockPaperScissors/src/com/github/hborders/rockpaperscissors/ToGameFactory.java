package com.github.hborders.rockpaperscissors;

public class ToGameFactory implements IGameFactory {
	private final Game.Provider gameProvider;

	public ToGameFactory() {
		this(new Game.Provider());
	}

	ToGameFactory(Game.Provider gameProvider) {
		this.gameProvider = gameProvider;
	}

	@Override
	public Game createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public ToGameFactory provide(Game.Provider gameProvider) {
			return new ToGameFactory(gameProvider);
		}
	}
}
