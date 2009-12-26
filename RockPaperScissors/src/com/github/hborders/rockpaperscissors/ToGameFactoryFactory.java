package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToGameFactoryFactory {

	private final GameCountFactory gameCountFactory;
	private final ToGameFactory.Provider toGameFactoryProvider;
	private final Game.Provider gameProvider;
	private final ToByGameFactoryFactory toByGameFactoryFactory;

	public ToGameFactoryFactory() {
		this(new GameCountFactory(), new ToGameFactory.Provider(),
				new Game.Provider(), new ToByGameFactoryFactory());
	}

	ToGameFactoryFactory(GameCountFactory gameCountFactory,
			ToGameFactory.Provider toGameFactoryProvider,
			Game.Provider gameProvider,
			ToByGameFactoryFactory toByGameFactoryFactory) {
		this.gameCountFactory = gameCountFactory;
		this.toGameFactoryProvider = toGameFactoryProvider;
		this.gameProvider = gameProvider;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
	}

	public IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args.length == 2) {
			try {
				gameCountFactory.createGameCount(args[1]);
				return toGameFactoryProvider.provide(gameProvider);
			} catch (InvalidGameCountException invalidGameCountException) {
			}
		} else if ((2 < args.length) && "-by".equals(args[2])) {
			return toByGameFactoryFactory.createGameFactory(args);
		}

		throw new InvalidGameArgumentsException();
	}
}
