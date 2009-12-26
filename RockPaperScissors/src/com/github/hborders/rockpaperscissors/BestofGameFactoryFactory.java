package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;
import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class BestofGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final BestofWonRoundCountFactory bestofWonRoundCountFactory;
	private final GameFactory.Provider gameFactoryProvider;
	private final Game.Provider gameProvider;
	private final Round round;

	BestofGameFactoryFactory(RoundCountFactory roundCountFactory,
			BestofWonRoundCountFactory bestofWonRoundCountFactory,
			GameFactory.Provider gameFactoryProvider,
			Game.Provider gameProvider, Round round) {
		this.roundCountFactory = roundCountFactory;
		this.bestofWonRoundCountFactory = bestofWonRoundCountFactory;
		this.gameFactoryProvider = gameFactoryProvider;
		this.gameProvider = gameProvider;
		this.round = round;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				RoundCount roundCount = roundCountFactory
						.createRoundCount(args[1]);
				WonRoundCount wonRoundCount = bestofWonRoundCountFactory
						.createWonRoundCount(roundCount);
				return gameFactoryProvider.provide(wonRoundCount, round,
						gameProvider);
			}
		} catch (InvalidWonRoundCountException invalidWonRoundCountException) {
		} catch (InvalidRoundCountException e) {
		}
		throw new InvalidGameArgumentsException();
	}
}
