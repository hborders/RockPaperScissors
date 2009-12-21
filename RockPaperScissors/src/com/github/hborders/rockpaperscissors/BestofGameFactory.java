package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class BestofGameFactory extends AbstractGameFactory {

	public BestofGameFactory() {
		this(new Game.Provider());
	}

	BestofGameFactory(Provider gameProvider) {
		super(gameProvider);
	}

	@Override
	public Game createGame(String[] args) throws InvalidGameArgumentsException {
		try {
			if ((args.length == 2) && 0 < new Integer(args[1])) {
				return gameProvider.provide();
			}
		} catch (NumberFormatException numberFormatException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
