package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToByGameFactoryFactory extends AbstractGameFactoryFactory {

	private final ToByGameFactory.Provider toByGameFactoryProvider;
	private final ToByGame.Provider toByGameProvider;

	public ToByGameFactoryFactory() {
		this(new GameCount.Provider(), new ToByGameFactory.Provider(),
				new ToByGame.Provider());
	}

	ToByGameFactoryFactory(GameCount.Provider gameCountProvider,
			ToByGameFactory.Provider toByGameFactoryProvider,
			ToByGame.Provider toByGameProvider) {
		super(gameCountProvider);

		this.toByGameFactoryProvider = toByGameFactoryProvider;
		this.toByGameProvider = toByGameProvider;
	}

	@Override
	public ToByGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 4) {
				gameCountProvider.provide(args[3]);
				return toByGameFactoryProvider.provide(toByGameProvider);
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
