package com.github.hborders.rockpaperscissors;


public class BestofGameFactoryFactory extends AbstractGameFactoryFactory {

	private final BestofGameFactory.Provider bestofGameFactoryProvider;
	private final BestofGame.Provider bestofGameProvider;

	public BestofGameFactoryFactory() {
		this(new CountConverter(), new BestofGameFactory.Provider(),
				new BestofGame.Provider());
	}

	BestofGameFactoryFactory(CountConverter countConverter,
			BestofGameFactory.Provider bestofGameFactoryProvider,
			BestofGame.Provider bestofGameProvider) {
		super(countConverter);

		this.bestofGameFactoryProvider = bestofGameFactoryProvider;
		this.bestofGameProvider = bestofGameProvider;
	}

	@Override
	public BestofGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				countConverter.convertCount(args[1]);
				return bestofGameFactoryProvider.provide(bestofGameProvider);
			}
		} catch (CountConverter.InvalidGameCountException invalidGameCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
