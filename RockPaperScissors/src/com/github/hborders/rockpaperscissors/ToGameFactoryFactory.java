package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;
import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final ToByGameFactoryFactory toByGameFactoryFactory;
	private final ToWonRoundCountFactory toWonRoundCountFactory;
	private final NoOpAfterPlayHookFactory noOpAfterPlayHookFactory;
	private final AttemptReader attemptReader;

	public ToGameFactoryFactory(RoundCountFactory roundCountFactory,
			ToByGameFactoryFactory toByGameFactoryFactory,
			ToWonRoundCountFactory toWonRoundCountFactory,
			NoOpAfterPlayHookFactory noOpAfterPlayHookFactory,
			AttemptReader attemptReader) {
		this.roundCountFactory = roundCountFactory;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
		this.toWonRoundCountFactory = toWonRoundCountFactory;
		this.noOpAfterPlayHookFactory = noOpAfterPlayHookFactory;
		this.attemptReader = attemptReader;
	}

	public GameFactory createGameFactory(String[] args)
			throws InvalidGameArgumentsException {
		if (2 <= args.length) {
			try {
				RoundCount toRoundCount = roundCountFactory
						.createRoundCount(args[1]);
				if (2 < args.length) {
					return toByGameFactoryFactory.createGameFactory(
							toRoundCount, args);
				}

				WonRoundCount winningWonRoundCount = toWonRoundCountFactory
						.createWinningWonRoundCount(toRoundCount);

				return new GameFactory(winningWonRoundCount,
						noOpAfterPlayHookFactory, attemptReader);
			} catch (InvalidRoundCountException invalidRoundCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
