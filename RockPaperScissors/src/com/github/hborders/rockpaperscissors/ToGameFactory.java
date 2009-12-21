package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

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
	public Game createGame(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
