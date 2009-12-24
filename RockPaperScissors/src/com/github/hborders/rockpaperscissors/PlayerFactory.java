package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Player.InvalidPlayerException;

public class PlayerFactory {
	private final Console console;
	private final Player.Provider playerProvider;

	public PlayerFactory() {
		this(new Console(), new Player.Provider());
	}

	PlayerFactory(Console console, Player.Provider playerProvider) {
		this.console = console;
		this.playerProvider = playerProvider;
	}

	public Player createPlayer(int playerNumber) {
		Player player = null;
		while (player == null) {
			try {
				String input = console.readLine("Player %d Name: ",
						playerNumber);
				player = playerProvider.provide(input, playerNumber);
			} catch (InvalidPlayerException invalidPlayerException) {
			}
		}

		return player;
	}
}
