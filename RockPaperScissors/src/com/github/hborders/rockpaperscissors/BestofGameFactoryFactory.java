package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofGameFactoryFactory {

	private final BestofWonRoundCountFactory bestofWonRoundCountFactory;
	private final GameFactory.Provider gameFactoryProvider;
	private final Game.Provider gameProvider;
	private final Round round;

	public BestofGameFactoryFactory() {
		this(new BestofWonRoundCountFactory(), new GameFactory.Provider(),
				new Game.Provider(), new Round());
	}

	BestofGameFactoryFactory(
			BestofWonRoundCountFactory bestofWonRoundCountFactory,
			GameFactory.Provider gameFactoryProvider,
			Game.Provider gameProvider, Round round) {
		this.bestofWonRoundCountFactory = bestofWonRoundCountFactory;
		this.gameFactoryProvider = gameFactoryProvider;
		this.gameProvider = gameProvider;
		this.round = round;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				WonRoundCount wonRoundCount = bestofWonRoundCountFactory
						.createWonRoundCount(args[1]);
				return gameFactoryProvider.provide(wonRoundCount, round,
						gameProvider);
			}
		} catch (InvalidWonRoundCountException invalidWonRoundCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
