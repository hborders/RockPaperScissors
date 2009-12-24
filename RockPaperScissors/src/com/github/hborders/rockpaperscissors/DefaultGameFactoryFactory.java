package com.github.hborders.rockpaperscissors;

public class DefaultGameFactoryFactory extends AbstractGameFactoryFactory {

	private final DefaultGameFactory.Provider defaultFactoryProvider;
	private final ToByGame.Provider toByGameProvider;
	private final ToGameFactoryFactory toGameFactoryFactory;
	private final BestofGameFactoryFactory bestofGameFactoryFactory;

	public DefaultGameFactoryFactory() {
		this(new CountConverter(), new DefaultGameFactory.Provider(),
				new ToByGame.Provider(), new ToGameFactoryFactory(),
				new BestofGameFactoryFactory());
	}

	DefaultGameFactoryFactory(CountConverter countConverter,
			DefaultGameFactory.Provider defaultFactoryProvider,
			ToByGame.Provider toByGameProvider,
			ToGameFactoryFactory toGameFactoryFactory,
			BestofGameFactoryFactory bestofGameFactoryFactory) {
		super(countConverter);
		this.defaultFactoryProvider = defaultFactoryProvider;
		this.toByGameProvider = toByGameProvider;
		this.toGameFactoryFactory = toGameFactoryFactory;
		this.bestofGameFactoryFactory = bestofGameFactoryFactory;
	}

	@Override
	public IGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (args == null) {
			throw new InvalidGameArgumentsException();
		}

		if (args.length == 0) {
			return defaultFactoryProvider.provide(toByGameProvider);
		} else if ("-to".equals(args[0])) {
			return toGameFactoryFactory.createGameFactory(args);
		} else if ("-bestof".equals(args[0])) {
			return bestofGameFactoryFactory.createGameFactory(args);
		} else {
			throw new InvalidGameArgumentsException();
		}
	}

}
