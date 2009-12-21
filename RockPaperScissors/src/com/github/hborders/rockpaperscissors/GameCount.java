package com.github.hborders.rockpaperscissors;

public class GameCount {
	private final int gameCount;

	public GameCount(String gameCount) throws InvalidGameCountException {
		try {
			this.gameCount = new Integer(gameCount);
			if (this.gameCount < 1) {
				throw new InvalidGameCountException();
			}
		} catch (NumberFormatException numberFormatException) {
			throw new InvalidGameCountException();
		}
	}

	static class Provider {
		public GameCount provide(String gameCount)
				throws InvalidGameCountException {
			return new GameCount(gameCount);
		}
	}

	public static class InvalidGameCountException extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
