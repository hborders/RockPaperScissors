package com.github.hborders.rockpaperscissors;

public class ToGameFactory implements IGameFactory {
	private final ToByGame.Provider toByGameProvider;

	public ToGameFactory() {
		this(new ToByGame.Provider());
	}

	ToGameFactory(ToByGame.Provider toByGameProvider) {
		super();
		this.toByGameProvider = toByGameProvider;
	}

	@Override
	public AbstractGame createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public ToGameFactory provide(ToByGame.Provider toByGameProvider) {
			return new ToGameFactory(toByGameProvider);
		}
	}
}
