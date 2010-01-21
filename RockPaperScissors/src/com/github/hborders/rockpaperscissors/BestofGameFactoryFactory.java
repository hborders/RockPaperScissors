package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;
import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;
import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class BestofGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final BestofWonRoundCountFactory bestofWonRoundCountFactory;
	private final NoOpAfterPlayHookFactory noOpAfterPlayHookFactory;
	private final AttemptReader attemptReader;

	public BestofGameFactoryFactory(RoundCountFactory roundCountFactory,
			BestofWonRoundCountFactory bestofWonRoundCountFactory,
			NoOpAfterPlayHookFactory noOpAfterPlayHookFactory,
			AttemptReader attemptReader) {
		this.roundCountFactory = roundCountFactory;
		this.bestofWonRoundCountFactory = bestofWonRoundCountFactory;
		this.noOpAfterPlayHookFactory = noOpAfterPlayHookFactory;
		this.attemptReader = attemptReader;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				RoundCount roundCount = roundCountFactory
						.createRoundCount(args[1]);
				WonRoundCount wonRoundCount = bestofWonRoundCountFactory
						.createWinningWonRoundCount(roundCount);
				return new GameFactory(wonRoundCount, noOpAfterPlayHookFactory,
						attemptReader);
			}
		} catch (InvalidWonRoundCountException invalidWonRoundCountException) {
		} catch (InvalidRoundCountException e) {
		}
		throw new InvalidGameArgumentsException();
	}
}
