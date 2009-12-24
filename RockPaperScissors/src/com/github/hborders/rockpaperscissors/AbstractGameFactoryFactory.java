package com.github.hborders.rockpaperscissors;

public abstract class AbstractGameFactoryFactory {
	final CountConverter gameCountCountConverter;

	AbstractGameFactoryFactory(CountConverter gameCountCountConverter) {
		this.gameCountCountConverter = gameCountCountConverter;
	}

	public abstract IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException;

	public static class InvalidGameArgumentsException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
