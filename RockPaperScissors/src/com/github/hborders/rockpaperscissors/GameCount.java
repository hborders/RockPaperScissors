package com.github.hborders.rockpaperscissors;

public class GameCount {
	public GameCount(String gameCount) throws InvalidGameCountException {
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
