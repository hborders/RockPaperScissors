package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToGameFactory extends AbstractGameFactory {

	private final ToByGameFactory toByGameFactory;

	public ToGameFactory() {
		this(new Game.Provider(), new GameCount.Provider(),
				new ToByGameFactory());
	}

	ToGameFactory(Provider gameProvider, GameCount.Provider gameCountProvider,
			ToByGameFactory toByGameFactory) {
		super(gameProvider, gameCountProvider);

		this.toByGameFactory = toByGameFactory;
	}

	@Override
	public Game createGame(String[] args) throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				gameCountProvider.provide(args[1]);
				return gameProvider.provide();
			} else if ((2 < args.length) && "-by".equals(args[2])) {
				return toByGameFactory.createGame(args);
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
