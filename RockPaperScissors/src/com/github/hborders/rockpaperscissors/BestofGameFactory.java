package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class BestofGameFactory extends AbstractGameFactory {

	public BestofGameFactory() {
		this(new Game.Provider(), new GameCount.Provider());
	}

	BestofGameFactory(Game.Provider gameProvider,
			GameCount.Provider gameCountProvider) {
		super(gameProvider, gameCountProvider);
	}

	@Override
	public Game createGame(String[] args) throws InvalidGameArgumentsException {
		try {
			if (args.length == 2) {
				gameCountProvider.provide(args[1]);
				return gameProvider.provide();
			}
		} catch (InvalidGameCountException invalidGameCountException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
