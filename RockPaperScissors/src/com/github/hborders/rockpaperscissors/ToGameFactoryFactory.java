package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToGameFactoryFactory {

	private final GameCountFactory gameCountFactory;
	private final ToGameFactory.Provider toGameFactoryProvider;
	private final ToByGame.Provider toByGameProvider;
	private final ToByGameFactoryFactory toByGameFactoryFactory;

	public ToGameFactoryFactory() {
		this(new GameCountFactory(), new ToGameFactory.Provider(),
				new ToByGame.Provider(), new ToByGameFactoryFactory());
	}

	ToGameFactoryFactory(GameCountFactory gameCountFactory,
			ToGameFactory.Provider toGameFactoryProvider,
			ToByGame.Provider toByGameProvider,
			ToByGameFactoryFactory toByGameFactoryFactory) {
		this.gameCountFactory = gameCountFactory;
		this.toGameFactoryProvider = toGameFactoryProvider;
		this.toByGameProvider = toByGameProvider;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
	}

	public IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args.length == 2) {
			try {
				gameCountFactory.createGameCount(args[1]);
				return toGameFactoryProvider.provide(toByGameProvider);
			} catch (InvalidGameCountException invalidGameCountException) {
			}
		} else if ((2 < args.length) && "-by".equals(args[2])) {
			return toByGameFactoryFactory.createGameFactory(args);
		}

		throw new InvalidGameArgumentsException();
	}
}
