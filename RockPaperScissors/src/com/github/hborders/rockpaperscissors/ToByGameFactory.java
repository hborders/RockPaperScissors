package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class ToByGameFactory extends AbstractGameFactory {

	public ToByGameFactory() {
		this(new Game.Provider());
	}

	ToByGameFactory(Provider gameProvider) {
		super(gameProvider);
	}

	@Override
	public Game createGame(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
