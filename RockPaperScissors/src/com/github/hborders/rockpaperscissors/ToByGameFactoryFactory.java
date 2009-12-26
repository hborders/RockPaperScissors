package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToByGameFactoryFactory {

	private final GameCountFactory gameCountFactory;
	private final ToByWonRoundCountFactory toByWonRoundCountFactory;
	private final Round.Provider roundProvider;
	private final AttemptReader attemptReader;
	private final ToByAfterPlayHook.Provider toByAfterPlayHookProvider;
	private final GameFactory.Provider gameFactoryProvider;
	private final Game.Provider gameProvider;

	public ToByGameFactoryFactory() {
		this(new GameCountFactory(), new ToByWonRoundCountFactory(),
				new Round.Provider(), new AttemptReader(),
				new ToByAfterPlayHook.Provider(), new GameFactory.Provider(),
				new Game.Provider());
	}

	ToByGameFactoryFactory(GameCountFactory gameCountFactory,
			ToByWonRoundCountFactory toByWonRoundCountFactory,
			Round.Provider roundProvider, AttemptReader attemptReader,
			ToByAfterPlayHook.Provider toByAfterPlayHookProvider,
			GameFactory.Provider gameFactoryProvider,
			Game.Provider gameProvider) {
		this.gameCountFactory = gameCountFactory;
		this.toByWonRoundCountFactory = toByWonRoundCountFactory;
		this.roundProvider = roundProvider;
		this.attemptReader = attemptReader;
		this.toByAfterPlayHookProvider = toByAfterPlayHookProvider;
		this.gameFactoryProvider = gameFactoryProvider;
		this.gameProvider = gameProvider;
	}

	public GameFactory createGameFactory(GameCount toGameCount, String[] args)
			throws InvalidGameArgumentsException {
		if (args.length == 4) {
			try {
				GameCount byGameCount = gameCountFactory
						.createGameCount(args[3]);
				WonRoundCount winningWonRoundCount = toByWonRoundCountFactory
						.createWonRoundCount(toGameCount, byGameCount);
				ToByAfterPlayHook toByAfterPlayHook = toByAfterPlayHookProvider
						.provide(winningWonRoundCount);
				Round round = roundProvider.provide(attemptReader,
						toByAfterPlayHook);
				return gameFactoryProvider.provide(winningWonRoundCount, round,
						gameProvider);
			} catch (InvalidGameCountException invalidGameCountException) {
			}
		}

		throw new InvalidGameArgumentsException();
	}
}
