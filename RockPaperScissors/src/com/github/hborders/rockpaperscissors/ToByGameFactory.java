package com.github.hborders.rockpaperscissors;

public class ToByGameFactory implements IGameFactory {
	private final Game.Provider gameProvider;

	public ToByGameFactory() {
		this(new Game.Provider());
	}

	ToByGameFactory(Game.Provider gameProvider) {
		this.gameProvider = gameProvider;
	}

	@Override
	public Game createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public ToByGameFactory provide(Game.Provider gameProvider) {
			return new ToByGameFactory(gameProvider);
		}
	}
}
