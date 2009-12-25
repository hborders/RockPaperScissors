package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToByGameFactoryFactory {

	private final GameCountFactory gameCountFactory;
	private final ToByGameFactory.Provider toByGameFactoryProvider;
	private final ToByGame.Provider toByGameProvider;

	public ToByGameFactoryFactory() {
		this(new GameCountFactory(), new ToByGameFactory.Provider(),
				new ToByGame.Provider());
	}

	ToByGameFactoryFactory(GameCountFactory gameCountFactory,
			ToByGameFactory.Provider toByGameFactoryProvider,
			ToByGame.Provider toByGameProvider) {
		this.gameCountFactory = gameCountFactory;
		this.toByGameFactoryProvider = toByGameFactoryProvider;
		this.toByGameProvider = toByGameProvider;
	}

	public ToByGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args.length == 4) {
			try {
				gameCountFactory.createGameCount(args[3]);
				return toByGameFactoryProvider.provide(toByGameProvider);
			} catch (InvalidGameCountException invalidGameCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
