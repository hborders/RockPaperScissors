package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public class DefaultGameFactory extends AbstractGameFactory {

	private final ToGameFactory toGameFactory;
	private final BestofGameFactory bestofGameFactory;

	public DefaultGameFactory() {
		this(new Game.Provider(), new UsagePrinter(), new ToGameFactory(),
				new BestofGameFactory());
	}

	DefaultGameFactory(Provider gameProvider, UsagePrinter usagePrinter,
			ToGameFactory toGameFactory, BestofGameFactory bestofGameFactory) {
		super(gameProvider, usagePrinter);
		this.toGameFactory = toGameFactory;
		this.bestofGameFactory = bestofGameFactory;
	}

	@Override
	public Game createGame(String[] args) {
		// TODO Auto-generated method stub
		return null;
	}

}
