package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class DefaultGameFactory implements IGameFactory {

	private final ToGameFactory toGameFactory;
	private final BestofGameFactory bestofGameFactory;
	private final Game.Provider gameProvider;

	public DefaultGameFactory() {
		this(new ToGameFactory(), new BestofGameFactory(), new Game.Provider());
	}

	DefaultGameFactory(ToGameFactory toGameFactory,
			BestofGameFactory bestofGameFactory, Provider gameProvider) {
		this.toGameFactory = toGameFactory;
		this.bestofGameFactory = bestofGameFactory;
		this.gameProvider = gameProvider;
	}

	@Override
	public Game createGame(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
