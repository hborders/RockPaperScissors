package com.github.hborders.rockpaperscissors;

public abstract class AbstractGameFactoryFactory {
	final GameCount.Provider gameCountProvider;

	AbstractGameFactoryFactory(GameCount.Provider gameCountProvider) {
		this.gameCountProvider = gameCountProvider;
	}

	public abstract IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException;

	public static class InvalidGameArgumentsException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
