package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public abstract class AbstractGameFactory {
	final Game.Provider gameProvider;

	AbstractGameFactory(Provider gameProvider) {
		this.gameProvider = gameProvider;
	}

	public abstract Game createGame(String[] args)
			throws InvalidGameArgumentsException;

	public static class InvalidGameArgumentsException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
