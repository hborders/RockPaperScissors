package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToByGameFactoryFactory extends AbstractGameFactoryFactory {

	public ToByGameFactoryFactory() {
		this(new GameFactory.Provider(), new GameCount.Provider());
	}

	ToByGameFactoryFactory(GameFactory.Provider gameFactoryProvider,
			GameCount.Provider gameCountProvider) {
		super(gameFactoryProvider, gameCountProvider);
	}

	@Override
	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 4) {
				gameCountProvider.provide(args[3]);
				return gameFactoryProvider.provide();
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
