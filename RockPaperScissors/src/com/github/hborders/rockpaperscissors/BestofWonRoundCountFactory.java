package com.github.hborders.rockpaperscissors;

public class BestofWonRoundCountFactory {

	public WonRoundCount createWonRoundCount(String input)
			throws InvalidWonRoundCountException {
		try {
			int wonRoundCount = new Integer(input);
			if ((0 < wonRoundCount) && ((wonRoundCount % 2) == 1)) {
				return new WonRoundCount(0);
			}
		} catch (NumberFormatException numberFormatException) {
		}

		throw new InvalidWonRoundCountException();
	}

	public static class InvalidWonRoundCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
