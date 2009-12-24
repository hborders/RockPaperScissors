package com.github.hborders.rockpaperscissors;

public abstract class AbstractGameFactoryFactory {
	final CountConverter countConverter;

	AbstractGameFactoryFactory(CountConverter countConverter) {
		this.countConverter = countConverter;
	}

	public abstract IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException;

	public static class InvalidGameArgumentsException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
