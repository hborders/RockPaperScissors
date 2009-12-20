package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Game.Provider;

public abstract class AbstractGameFactory {
	final Game.Provider gameProvider;
	final UsagePrinter usagePrinter;

	AbstractGameFactory(Provider gameProvider, UsagePrinter usagePrinter) {
		this.gameProvider = gameProvider;
		this.usagePrinter = usagePrinter;
	}

	public abstract Game createGame(String[] args);
}
