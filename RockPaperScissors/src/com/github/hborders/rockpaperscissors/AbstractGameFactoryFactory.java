package com.github.hborders.rockpaperscissors;

public abstract class AbstractGameFactoryFactory {
	final GameFactory.Provider gameFactoryProvider;
	final GameCount.Provider gameCountProvider;

	AbstractGameFactoryFactory(GameFactory.Provider gameFactoryProvider,
			GameCount.Provider gameCountProvider) {
		this.gameFactoryProvider = gameFactoryProvider;
		this.gameCountProvider = gameCountProvider;
	}

	public abstract GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException;

	public static class InvalidGameArgumentsException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
