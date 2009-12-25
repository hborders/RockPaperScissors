package com.github.hborders.rockpaperscissors;

public class GameCountFactory {

	public GameCount createGameCount(String input)
			throws InvalidGameCountException {
		try {
			if (0 < new Integer(input)) {
				return new GameCount();
			}
		} catch (NumberFormatException numberFormatException) {
		}

		throw new InvalidGameCountException();
	}

	public static class InvalidGameCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
