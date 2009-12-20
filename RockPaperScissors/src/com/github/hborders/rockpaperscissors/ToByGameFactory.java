package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class ToByGameFactory extends AbstractGameFactory {

	public ToByGameFactory() {
		this(new Game.Provider(), new UsagePrinter());
	}

	ToByGameFactory(Provider gameProvider, UsagePrinter usagePrinter) {
		super(gameProvider, usagePrinter);
	}

	@Override
	public Game createGame(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
