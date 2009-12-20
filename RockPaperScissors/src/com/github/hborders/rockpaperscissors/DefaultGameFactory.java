package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class DefaultGameFactory extends AbstractGameFactory {

	private final ToGameFactory toGameFactory;
	private final BestofGameFactory bestofGameFactory;

	public DefaultGameFactory() {
		this(new Game.Provider(), new ToGameFactory(), new BestofGameFactory());
	}

	DefaultGameFactory(Provider gameProvider, ToGameFactory toGameFactory,
			BestofGameFactory bestofGameFactory) {
		super(gameProvider);
		this.toGameFactory = toGameFactory;
		this.bestofGameFactory = bestofGameFactory;
	}

	@Override
	public Game createGame(String[] args) throws InvalidGameArgumentsException {
		return gameProvider.provide();
	}

}
