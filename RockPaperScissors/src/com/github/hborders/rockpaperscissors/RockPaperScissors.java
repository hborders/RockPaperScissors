package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class RockPaperScissors {
	public static void main(String[] args) {
		new RockPaperScissors().play(args);
	}

	private final UsagePrinter usagePrinter;
	private final DefaultGameFactory defaultGameFactory;

	public RockPaperScissors() {
		this(new UsagePrinter(), new DefaultGameFactory());
	}

	public RockPaperScissors(UsagePrinter usagePrinter,
			DefaultGameFactory defaultGameFactory) {
		this.usagePrinter = usagePrinter;
		this.defaultGameFactory = defaultGameFactory;
	}

	public void play(String[] args) {
		try {
			defaultGameFactory.createGame(args);
		} catch (InvalidGameArgumentsException invalidGameArgumentsException) {
			usagePrinter.printUsage();
		}
	}
}
