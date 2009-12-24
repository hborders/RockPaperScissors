package com.github.hborders.rockpaperscissors;


public class ToGameFactoryFactory extends AbstractGameFactoryFactory {

	private final ToGameFactory.Provider toGameFactoryProvider;
	private final ToByGame.Provider toByGameProvider;
	private final ToByGameFactoryFactory toByGameFactoryFactory;

	public ToGameFactoryFactory() {
		this(new CountConverter(), new ToGameFactory.Provider(),
				new ToByGame.Provider(), new ToByGameFactoryFactory());
	}

	ToGameFactoryFactory(CountConverter countConverter,
			ToGameFactory.Provider toGameFactoryProvider,
			ToByGame.Provider toByGameProvider,
			ToByGameFactoryFactory toByGameFactoryFactory) {
		super(countConverter);

		this.toGameFactoryProvider = toGameFactoryProvider;
		this.toByGameProvider = toByGameProvider;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
	}

	@Override
	public IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				countConverter.convertCount(args[1]);
				return toGameFactoryProvider.provide(toByGameProvider);
			} else if ((2 < args.length) && "-by".equals(args[2])) {
				return toByGameFactoryFactory.createGameFactory(args);
			}
		} catch (CountConverter.InvalidCountException invalidCountException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
