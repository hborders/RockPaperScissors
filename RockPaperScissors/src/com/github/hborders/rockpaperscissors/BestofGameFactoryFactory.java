package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;
import com.github.hborders.rockpaperscissors.GameFactory.Provider;
import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;
import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;
import com.google.inject.Inject;

public class BestofGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final BestofWonRoundCountFactory bestofWonRoundCountFactory;
	private final GameFactory.Provider gameFactoryProvider;
	private final NoOpAfterPlayHookFactory noOpAfterPlayHookFactory;
	private final AttemptReader attemptReader;
	private final Round.Provider roundProvider;
	private final Game.Provider gameProvider;

	@Inject
	public BestofGameFactoryFactory(RoundCountFactory roundCountFactory,
			BestofWonRoundCountFactory bestofWonRoundCountFactory,
			Provider gameFactoryProvider,
			NoOpAfterPlayHookFactory noOpAfterPlayHookFactory,
			AttemptReader attemptReader,
			com.github.hborders.rockpaperscissors.Round.Provider roundProvider,
			com.github.hborders.rockpaperscissors.Game.Provider gameProvider) {
		this.roundCountFactory = roundCountFactory;
		this.bestofWonRoundCountFactory = bestofWonRoundCountFactory;
		this.gameFactoryProvider = gameFactoryProvider;
		this.noOpAfterPlayHookFactory = noOpAfterPlayHookFactory;
		this.attemptReader = attemptReader;
		this.roundProvider = roundProvider;
		this.gameProvider = gameProvider;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				RoundCount roundCount = roundCountFactory
						.createRoundCount(args[1]);
				WonRoundCount wonRoundCount = bestofWonRoundCountFactory
						.createWinningWonRoundCount(roundCount);
				return gameFactoryProvider.provide(wonRoundCount,
						noOpAfterPlayHookFactory, attemptReader, roundProvider,
						gameProvider);
			}
		} catch (InvalidWonRoundCountException invalidWonRoundCountException) {
		} catch (InvalidRoundCountException e) {
		}
		throw new InvalidGameArgumentsException();
	}
}
