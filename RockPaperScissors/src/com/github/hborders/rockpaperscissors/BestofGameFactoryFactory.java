package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofGameFactory.InvalidGameCountException;
import com.github.hborders.rockpaperscissors.CountConverter.InvalidCountException;

public class BestofGameFactoryFactory extends AbstractGameFactoryFactory {

	private final BestofGameFactory.Provider bestofGameFactoryProvider;
	private final BestofGame.Provider bestofGameProvider;
	private final Round round;

	public BestofGameFactoryFactory() {
		this(new CountConverter(), new BestofGameFactory.Provider(),
				new BestofGame.Provider(), new Round());
	}

	BestofGameFactoryFactory(CountConverter countConverter,
			BestofGameFactory.Provider bestofGameFactoryProvider,
			BestofGame.Provider bestofGameProvider, Round round) {
		super(countConverter);

		this.bestofGameFactoryProvider = bestofGameFactoryProvider;
		this.bestofGameProvider = bestofGameProvider;
		this.round = round;
	}

	@Override
	public BestofGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				int gameCount = countConverter.convertCount(args[1]);
				return bestofGameFactoryProvider.provide(gameCount,
						bestofGameProvider, round);
			}
		} catch (InvalidCountException invalidCountException) {
		} catch (InvalidGameCountException invalidGameCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
