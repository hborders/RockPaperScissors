package com.github.hborders.rockpaperscissors;

public class PlayerFactory {
	private final Console console;
	private final WonRoundCount.Provider wonRoundCountProvider;
	private final Player.Provider playerProvider;

	public PlayerFactory() {
		this(new Console(), new WonRoundCount.Provider(), new Player.Provider());
	}

	PlayerFactory(Console console,
			WonRoundCount.Provider wonRoundCountProvider,
			Player.Provider playerProvider) {
		this.console = console;
		this.wonRoundCountProvider = wonRoundCountProvider;
		this.playerProvider = playerProvider;
	}

	public Player createPlayer(int playerNumber) {
		Player player = null;
		while (player == null) {
			String input = console.readLine("Player %d Name: ", playerNumber);
			if (input != null) {
				String rawPlayerName = input.trim();
				if (rawPlayerName.length() > 0) {
					WonRoundCount wonRoundCount = wonRoundCountProvider
							.provide(0);
					player = playerProvider.provide(rawPlayerName,
							playerNumber, wonRoundCount);
				}
			}
		}

		return player;
	}
}
