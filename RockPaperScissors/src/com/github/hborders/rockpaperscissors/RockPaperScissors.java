package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;

public class RockPaperScissors {
	public static void main(String[] args) {
		new RockPaperScissors().play(args);
	}

	private final UsagePrinter usagePrinter;
	private final DefaultGameFactoryFactory defaultGameFactoryFactory;
	private final PlayerFactory playerFactory;

	public RockPaperScissors() {
		this(new UsagePrinter(), new DefaultGameFactoryFactory(),
				new PlayerFactory());
	}

	public RockPaperScissors(UsagePrinter usagePrinter,
			DefaultGameFactoryFactory defaultGameFactoryFactory,
			PlayerFactory playerFactory) {
		this.usagePrinter = usagePrinter;
		this.defaultGameFactoryFactory = defaultGameFactoryFactory;
		this.playerFactory = playerFactory;
	}

	public void play(String[] args) {
		try {
			IGameFactory gameFactory = defaultGameFactoryFactory
					.createGameFactory(args);
			Player firstPlayer = playerFactory.createPlayer(1);
			Player secondPlayer = playerFactory.createPlayer(2);
			IGame game = gameFactory.createGame(firstPlayer, secondPlayer);
			game.play();
		} catch (InvalidGameArgumentsException invalidGameArgumentsException) {
			usagePrinter.printUsage();
		}
	}
}
