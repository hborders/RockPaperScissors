package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToGameFactoryFactory {

	private final GameCountFactory gameCountFactory;
	private final ToWonRoundCountFactory toWonRoundCountFactory;
	private final ToByGameFactoryFactory toByGameFactoryFactory;
	private final Game.Provider gameProvider;
	private final Round round;
	private final GameFactory.Provider gameFactoryProvider;

	public ToGameFactoryFactory() {
		this(new GameCountFactory(), new ToWonRoundCountFactory(),
				new ToByGameFactoryFactory(), new Game.Provider(), new Round(),
				new GameFactory.Provider());
	}

	public ToGameFactoryFactory(GameCountFactory gameCountFactory,
			ToWonRoundCountFactory toWonRoundCountFactory,
			ToByGameFactoryFactory toByGameFactoryFactory,
			Game.Provider gameProvider, Round round,
			GameFactory.Provider gameFactoryProvider) {
		this.gameCountFactory = gameCountFactory;
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
				GameCount toGameCount = gameCountFactory
						.createGameCount(args[1]);
				if (2 < args.length) {
					return toByGameFactoryFactory.createGameFactory(
							toGameCount, args);
				}

				WonRoundCount winningWonRoundCount = toWonRoundCountFactory
						.createWonRoundCount(toGameCount);

				return gameFactoryProvider.provide(winningWonRoundCount, round,
						gameProvider);
			} catch (InvalidGameCountException invalidGameCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
