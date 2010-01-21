package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

public class PlayerFactory {
	private final BufferedReader bufferedReader;
	private final Writer writer;

	public PlayerFactory(BufferedReader bufferedReader, Writer writer) {
		this.bufferedReader = bufferedReader;
		this.writer = writer;
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
					WonRoundCount wonRoundCount = new WonRoundCount(0);
					player = new Player(rawPlayerName, playerNumber,
							wonRoundCount);
				}
			}
		}

		return player;
	}
}
