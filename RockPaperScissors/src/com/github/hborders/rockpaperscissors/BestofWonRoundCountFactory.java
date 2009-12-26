package com.github.hborders.rockpaperscissors;

public class BestofWonRoundCountFactory {

	public WonRoundCount createWonRoundCount(RoundCount roundCount)
			throws InvalidWonRoundCountException {
		int rawRoundCount = roundCount.getRawRoundCount();
		if ((rawRoundCount % 2) == 1) {
			return new WonRoundCount((rawRoundCount / 2) + 1);
		}

		throw new InvalidWonRoundCountException();
	}

	public static class InvalidWonRoundCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
