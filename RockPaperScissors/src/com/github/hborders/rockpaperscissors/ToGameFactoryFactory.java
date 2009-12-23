package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToGameFactoryFactory extends AbstractGameFactoryFactory {

	private final ToGameFactory.Provider toGameFactoryProvider;
	private final ToByGame.Provider toByGameProvider;
	private final ToByGameFactoryFactory toByGameFactoryFactory;

	public ToGameFactoryFactory() {
		this(new GameCount.Provider(), new ToGameFactory.Provider(),
				new ToByGame.Provider(), new ToByGameFactoryFactory());
	}

	ToGameFactoryFactory(GameCount.Provider gameCountProvider,
			ToGameFactory.Provider toGameFactoryProvider,
			ToByGame.Provider toByGameProvider,
			ToByGameFactoryFactory toByGameFactoryFactory) {
		super(gameCountProvider);

		this.toGameFactoryProvider = toGameFactoryProvider;
		this.toByGameProvider = toByGameProvider;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
	}

	@Override
	public IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				gameCountProvider.provide(args[1]);
				return toGameFactoryProvider.provide(toByGameProvider);
			} else if ((2 < args.length) && "-by".equals(args[2])) {
				return toByGameFactoryFactory.createGameFactory(args);
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
