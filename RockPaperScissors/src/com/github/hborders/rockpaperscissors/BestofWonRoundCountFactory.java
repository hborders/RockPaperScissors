package com.github.hborders.rockpaperscissors;

public class BestofWonRoundCountFactory {

	public WonRoundCount createWonRoundCount(RoundCount roundCount)
			throws InvalidWonRoundCountException {
		int wonRoundCount = roundCount.getRoundCount();
		if ((wonRoundCount % 2) == 1) {
			return new WonRoundCount(wonRoundCount);
		}

		throw new InvalidWonRoundCountException();
	}

	public static class InvalidWonRoundCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
