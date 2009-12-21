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
		// TODO Auto-generated method stub
		return null;
	}

}
