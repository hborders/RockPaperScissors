package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class BestofGameFactory extends AbstractGameFactory {

	public BestofGameFactory() {
		this(new Game.Provider(), new UsagePrinter());
	}

	BestofGameFactory(Provider gameProvider, UsagePrinter usagePrinter) {
		super(gameProvider, usagePrinter);
	}

	@Override
	public Game createGame(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
