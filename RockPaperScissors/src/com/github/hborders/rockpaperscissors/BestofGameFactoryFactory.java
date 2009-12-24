package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofGameFactory.InvalidGameCountException;
import com.github.hborders.rockpaperscissors.CountConverter.InvalidCountException;

public class BestofGameFactoryFactory extends AbstractGameFactoryFactory {

	private final BestofGameFactory.Provider bestofGameFactoryProvider;
	private final BestofGame.Provider bestofGameProvider;
	private final Round.Provider roundProvider;
	private final AttemptReader attemptReader;

	public BestofGameFactoryFactory() {
		this(new CountConverter(), new BestofGameFactory.Provider(),
				new BestofGame.Provider(), new Round.Provider(),
				new AttemptReader());
	}

	BestofGameFactoryFactory(CountConverter countConverter,
			BestofGameFactory.Provider bestofGameFactoryProvider,
			BestofGame.Provider bestofGameProvider,
			Round.Provider roundProvider, AttemptReader attemptReader) {
		super(countConverter);

		this.bestofGameFactoryProvider = bestofGameFactoryProvider;
		this.bestofGameProvider = bestofGameProvider;
		this.roundProvider = roundProvider;
		this.attemptReader = attemptReader;
	}

	@Override
	public BestofGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				int gameCount = countConverter.convertCount(args[1]);
				return bestofGameFactoryProvider.provide(gameCount,
						bestofGameProvider, roundProvider, attemptReader);
			}
		} catch (InvalidCountException invalidCountException) {
		} catch (InvalidGameCountException invalidGameCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
