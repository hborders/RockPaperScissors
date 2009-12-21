package com.github.hborders.rockpaperscissors;

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

	public Player createPlayer(int number) {
		return null;
	}
}
