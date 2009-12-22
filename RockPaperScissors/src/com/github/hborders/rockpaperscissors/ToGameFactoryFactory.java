package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToGameFactoryFactory extends AbstractGameFactoryFactory {

	private final ToByGameFactoryFactory toByGameFactoryFactory;

	public ToGameFactoryFactory() {
		this(new GameFactory.Provider(), new GameCount.Provider(),
				new ToByGameFactoryFactory());
	}

	ToGameFactoryFactory(GameFactory.Provider gameFactoryProvider,
			GameCount.Provider gameCountProvider,
			ToByGameFactoryFactory toByGameFactoryFactory) {
		super(gameFactoryProvider, gameCountProvider);

		this.toByGameFactoryFactory = toByGameFactoryFactory;
	}

	@Override
	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				gameCountProvider.provide(args[1]);
				return gameFactoryProvider.provide();
			} else if ((2 < args.length) && "-by".equals(args[2])) {
				return toByGameFactoryFactory.createGameFactory(args);
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
