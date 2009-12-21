package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class DefaultGameFactory extends AbstractGameFactory {

	private final ToGameFactory toGameFactory;
	private final BestofGameFactory bestofGameFactory;

	public DefaultGameFactory() {
		this(new Game.Provider(), new GameCount.Provider(),
				new ToGameFactory(), new BestofGameFactory());
	}

	DefaultGameFactory(Provider gameProvider,
			GameCount.Provider gameCountProvider, ToGameFactory toGameFactory,
			BestofGameFactory bestofGameFactory) {
		super(gameProvider, null);
		this.toGameFactory = toGameFactory;
		this.bestofGameFactory = bestofGameFactory;
	}

	@Override
	public Game createGame(String[] args) throws InvalidGameArgumentsException {
		if (args == null) {
			throw new InvalidGameArgumentsException();
		}

		if (args.length == 0) {
			return gameProvider.provide();
		} else if ("-to".equals(args[0])) {
			return toGameFactory.createGame(args);
		} else if ("-bestof".equals(args[0])) {
			return bestofGameFactory.createGame(args);
		} else {
			throw new InvalidGameArgumentsException();
		}
	}

}
