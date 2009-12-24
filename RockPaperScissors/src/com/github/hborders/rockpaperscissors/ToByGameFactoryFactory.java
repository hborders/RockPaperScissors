package com.github.hborders.rockpaperscissors;


public class ToByGameFactoryFactory extends AbstractGameFactoryFactory {

	private final ToByGameFactory.Provider toByGameFactoryProvider;
	private final ToByGame.Provider toByGameProvider;

	public ToByGameFactoryFactory() {
		this(new CountConverter(), new ToByGameFactory.Provider(),
				new ToByGame.Provider());
	}

	ToByGameFactoryFactory(CountConverter countConverter,
			ToByGameFactory.Provider toByGameFactoryProvider,
			ToByGame.Provider toByGameProvider) {
		super(countConverter);

		this.toByGameFactoryProvider = toByGameFactoryProvider;
		this.toByGameProvider = toByGameProvider;
	}

	@Override
	public ToByGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 4) {
				countConverter.convertCount(args[3]);
				return toByGameFactoryProvider.provide(toByGameProvider);
			}
		} catch (CountConverter.InvalidGameCountException invalidGameCountException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
