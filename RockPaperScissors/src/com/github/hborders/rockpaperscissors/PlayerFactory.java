package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

import com.google.inject.Inject;

public class PlayerFactory {
	private final BufferedReader bufferedReader;
	private final Writer writer;
	private final WonRoundCount.Provider wonRoundCountProvider;
	private final Player.Provider playerProvider;

	@Inject
	public PlayerFactory(BufferedReader bufferedReader, Writer writer,
			WonRoundCount.Provider wonRoundCountProvider,
			Player.Provider playerProvider) {
		this.bufferedReader = bufferedReader;
		this.writer = writer;
		this.wonRoundCountProvider = wonRoundCountProvider;
		this.playerProvider = playerProvider;
	}

	public Player createPlayer(int playerNumber) throws IOException {
		Player player = null;
		while (player == null) {
			writer.write("Player " + playerNumber + " Name: ");
			writer.flush();
			String input = bufferedReader.readLine();
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
