package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class RockPaperScissors {
	public static void main(String[] args) {
		new RockPaperScissors().play(args);
	}

	private final UsagePrinter usagePrinter;
	private final DefaultGameFactory defaultGameFactory;
	private final PlayerFactory playerFactory;

	public RockPaperScissors() {
		this(new UsagePrinter(), new DefaultGameFactory(), new PlayerFactory());
	}

	public RockPaperScissors(UsagePrinter usagePrinter,
			DefaultGameFactory defaultGameFactory, PlayerFactory playerFactory) {
		this.usagePrinter = usagePrinter;
		this.defaultGameFactory = defaultGameFactory;
		this.playerFactory = playerFactory;
	}

	public void play(String[] args) {
		try {
			defaultGameFactory.createGame(args);
			playerFactory.createPlayer(1);
			playerFactory.createPlayer(2);
		} catch (InvalidGameArgumentsException invalidGameArgumentsException) {
			usagePrinter.printUsage();
		}
	}
}
