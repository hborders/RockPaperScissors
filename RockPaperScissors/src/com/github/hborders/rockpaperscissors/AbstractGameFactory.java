package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public abstract class AbstractGameFactory {
	final Game.Provider gameProvider;
	final GameCount.Provider gameCountProvider;

	AbstractGameFactory(Provider gameProvider,
			GameCount.Provider gameCountProvider) {
		this.gameProvider = gameProvider;
		this.gameCountProvider = gameCountProvider;
	}

	public abstract Game createGame(String[] args)
			throws InvalidGameArgumentsException;

	public static class InvalidGameArgumentsException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
