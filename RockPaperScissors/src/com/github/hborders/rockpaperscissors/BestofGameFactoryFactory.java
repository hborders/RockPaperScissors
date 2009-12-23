package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class BestofGameFactoryFactory extends AbstractGameFactoryFactory {

	private final BestofGameFactory.Provider bestofGameFactoryProvider;
	private final BestofGame.Provider bestofGameProvider;

	public BestofGameFactoryFactory() {
		this(new GameCount.Provider(), new BestofGameFactory.Provider(),
				new BestofGame.Provider());
	}

	BestofGameFactoryFactory(GameCount.Provider gameCountProvider,
			BestofGameFactory.Provider bestofGameFactoryProvider,
			BestofGame.Provider bestofGameProvider) {
		super(gameCountProvider);

		this.bestofGameFactoryProvider = bestofGameFactoryProvider;
		this.bestofGameProvider = bestofGameProvider;
	}

	@Override
	public BestofGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				gameCountProvider.provide(args[1]);
				return bestofGameFactoryProvider.provide(bestofGameProvider);
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
