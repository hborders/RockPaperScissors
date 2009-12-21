package com.github.hborders.rockpaperscissors;


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
			if ((args.length == 2) && 0 < new Integer(args[1])) {
				return gameProvider.provide();
			}
		} catch (NumberFormatException numberFormatException) {
		}
		throw new InvalidGameArgumentsException();
	}

}
