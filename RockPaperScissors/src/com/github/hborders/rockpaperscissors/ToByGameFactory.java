package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class ToByGameFactory extends AbstractGameFactory {

	public ToByGameFactory() {
		this(new Game.Provider(), new GameCount.Provider());
	}

	ToByGameFactory(Provider gameProvider, GameCount.Provider gameCountProvider) {
		super(gameProvider, gameCountProvider);
	}

	@Override
	public Game createGame(String[] args) throws InvalidGameArgumentsException {
		try {
			if ((args.length == 4) && (0 < new Integer(args[3]))) {
				return gameProvider.provide();
			}
		} catch (NumberFormatException numberFormatException) {
		}

		throw new InvalidGameArgumentsException();
	}

}
