package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class ToGameFactory extends AbstractGameFactory {

	private final ToByGameFactory toByGameFactory;

	public ToGameFactory() {
		this(new Game.Provider(), new UsagePrinter(), new ToByGameFactory());
	}

	ToGameFactory(Provider gameProvider, UsagePrinter usagePrinter,
			ToByGameFactory toByGameFactory) {
		super(gameProvider, usagePrinter);

		this.toByGameFactory = toByGameFactory;
	}

	@Override
	public Game createGame(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
