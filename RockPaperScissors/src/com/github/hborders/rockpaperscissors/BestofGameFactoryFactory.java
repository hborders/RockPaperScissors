package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofGameFactoryFactory {

	private final BestofWonRoundCountFactory bestofWonRoundCountFactory;
	private final BestofGameFactory.Provider bestofGameFactoryProvider;
	private final Game.Provider gameProvider;
	private final Round round;

	public BestofGameFactoryFactory() {
		this(new BestofWonRoundCountFactory(),
				new BestofGameFactory.Provider(), new Game.Provider(),
				new Round());
	}

	BestofGameFactoryFactory(
			BestofWonRoundCountFactory bestofWonRoundCountFactory,
			BestofGameFactory.Provider bestofGameFactoryProvider,
			Game.Provider gameProvider, Round round) {
		this.bestofWonRoundCountFactory = bestofWonRoundCountFactory;
		this.bestofGameFactoryProvider = bestofGameFactoryProvider;
		this.gameProvider = gameProvider;
		this.round = round;
	}

	public BestofGameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				WonRoundCount wonRoundCount = bestofWonRoundCountFactory
						.createWonRoundCount(args[1]);
				return bestofGameFactoryProvider.provide(gameProvider,
						wonRoundCount, round);
			}
		} catch (InvalidWonRoundCountException invalidWonRoundCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
