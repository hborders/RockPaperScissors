package com.github.hborders.rockpaperscissors;

public class DefaultGameFactory implements IGameFactory {
	private final ToByGame.Provider toByGameProvider;

	public DefaultGameFactory() {
		this(new ToByGame.Provider());
	}

	DefaultGameFactory(ToByGame.Provider toByGameProvider) {
		this.toByGameProvider = toByGameProvider;
	}

	@Override
	public IGame createGame(Player firstPlayer, Player secondPlayer) {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public DefaultGameFactory provide(ToByGame.Provider toByGameProvider) {
			return new DefaultGameFactory(toByGameProvider);
		}
	}
}
