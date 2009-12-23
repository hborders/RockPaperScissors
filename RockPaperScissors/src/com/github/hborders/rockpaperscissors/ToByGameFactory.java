package com.github.hborders.rockpaperscissors;

public class ToByGameFactory implements IGameFactory {
	private final ToByGame.Provider toByGameProvider;

	public ToByGameFactory() {
		this(new ToByGame.Provider());
	}

	ToByGameFactory(ToByGame.Provider toByGameProvider) {
		this.toByGameProvider = toByGameProvider;
	}

	@Override
	public AbstractGame createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public ToByGameFactory provide(ToByGame.Provider toByGameProvider) {
			return new ToByGameFactory(toByGameProvider);
		}
	}
}
