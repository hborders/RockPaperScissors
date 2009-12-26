package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToGameFactoryFactory {

	private final RoundCountFactory roundCountFactory;
	private final ToWonRoundCountFactory toWonRoundCountFactory;
	private final ToByGameFactoryFactory toByGameFactoryFactory;
	private final Game.Provider gameProvider;
	private final Round round;
	private final GameFactory.Provider gameFactoryProvider;

	public ToGameFactoryFactory() {
		this(new RoundCountFactory(), new ToWonRoundCountFactory(),
				new ToByGameFactoryFactory(), new Game.Provider(), new Round(),
				new GameFactory.Provider());
	}

	public ToGameFactoryFactory(RoundCountFactory roundCountFactory,
			ToWonRoundCountFactory toWonRoundCountFactory,
			ToByGameFactoryFactory toByGameFactoryFactory,
			Game.Provider gameProvider, Round round,
			GameFactory.Provider gameFactoryProvider) {
		this.roundCountFactory = roundCountFactory;
		this.toWonRoundCountFactory = toWonRoundCountFactory;
		this.toByGameFactoryFactory = toByGameFactoryFactory;
		this.gameProvider = gameProvider;
		this.round = round;
		this.gameFactoryProvider = gameFactoryProvider;
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
						.createWonRoundCount(toRoundCount);

				return gameFactoryProvider.provide(winningWonRoundCount, round,
						gameProvider);
			} catch (InvalidRoundCountException invalidRoundCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
