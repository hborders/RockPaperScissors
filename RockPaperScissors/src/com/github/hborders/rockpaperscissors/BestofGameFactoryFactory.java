package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofGameFactoryFactory {

	private final BestofWonRoundCountFactory bestofWonRoundCountFactory;
	private final BestofGameFactory.Provider bestofGameFactoryProvider;
	private final BestofGame.Provider bestofGameProvider;
	private final Round round;

	public BestofGameFactoryFactory() {
		this(new BestofWonRoundCountFactory(),
				new BestofGameFactory.Provider(), new BestofGame.Provider(),
				new Round());
	}

	BestofGameFactoryFactory(
			BestofWonRoundCountFactory bestofWonRoundCountFactory,
			BestofGameFactory.Provider bestofGameFactoryProvider,
			BestofGame.Provider bestofGameProvider, Round round) {
		this.bestofWonRoundCountFactory = bestofWonRoundCountFactory;
		this.bestofGameFactoryProvider = bestofGameFactoryProvider;
		this.bestofGameProvider = bestofGameProvider;
		this.round = round;
	}

	public BestofGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				WonRoundCount wonRoundCount = bestofWonRoundCountFactory
						.createWonRoundCount(args[1]);
				return bestofGameFactoryProvider.provide(bestofGameProvider,
						wonRoundCount, round);
			}
		} catch (InvalidWonRoundCountException invalidWonRoundCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
