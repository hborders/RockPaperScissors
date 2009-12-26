package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToByGameFactoryFactory {

	private final GameCountFactory gameCountFactory;
	private final ToByGameFactory.Provider toByGameFactoryProvider;
	private final Game.Provider gameProvider;

	public ToByGameFactoryFactory() {
		this(new GameCountFactory(), new ToByGameFactory.Provider(),
				new Game.Provider());
	}

	ToByGameFactoryFactory(GameCountFactory gameCountFactory,
			ToByGameFactory.Provider toByGameFactoryProvider,
			Game.Provider gameProvider) {
		this.gameCountFactory = gameCountFactory;
		this.toByGameFactoryProvider = toByGameFactoryProvider;
		this.gameProvider = gameProvider;
	}

	public ToByGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args.length == 4) {
			try {
				gameCountFactory.createGameCount(args[3]);
				return toByGameFactoryProvider.provide(gameProvider);
			} catch (InvalidGameCountException invalidGameCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
