package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class BestofGameFactoryFactory extends AbstractGameFactoryFactory {

	public BestofGameFactoryFactory() {
		this(new GameFactory.Provider(), new GameCount.Provider());
	}

	BestofGameFactoryFactory(GameFactory.Provider gameFactoryProvider,
			GameCount.Provider gameCountProvider) {
		super(gameFactoryProvider, gameCountProvider);
	}

	@Override
	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				gameCountProvider.provide(args[1]);
				return gameFactoryProvider.provide();
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
