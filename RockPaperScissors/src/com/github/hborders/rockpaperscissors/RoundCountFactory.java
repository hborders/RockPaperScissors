package com.github.hborders.rockpaperscissors;

public class RoundCountFactory {

	public RoundCount createRoundCount(String input)
			throws InvalidRoundCountException {
		try {
			int roundCount = new Integer(input);
			if (0 < roundCount) {
				return new RoundCount(roundCount);
			}
		} catch (NumberFormatException numberFormatException) {
		}

		throw new InvalidRoundCountException();
	}

	public static class InvalidRoundCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
