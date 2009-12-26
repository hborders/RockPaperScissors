package com.github.hborders.rockpaperscissors;

public class DefaultGameFactory implements IGameFactory {
	private final Game.Provider gameProvider;

	public DefaultGameFactory() {
		this(new Game.Provider());
	}

	DefaultGameFactory(Game.Provider gameProvider) {
		this.gameProvider = gameProvider;
	}

	@Override
	public Game createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public DefaultGameFactory provide(Game.Provider gameProvider) {
			return new DefaultGameFactory(gameProvider);
		}
	}
}
