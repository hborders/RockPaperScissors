package com.github.hborders.rockpaperscissors;

public class Round {
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final AttemptFactory attemptFactory;

	public Round(Player firstPlayer, Player secondPlayer) {
		this(firstPlayer, secondPlayer, new AttemptFactory());
	}

	public Round(Player firstPlayer, Player secondPlayer,
			AttemptFactory attemptFactory) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.attemptFactory = attemptFactory;
	}

	public Player play() {
		return null;
	}

	static class Provider {
		public Round provide(Player firstPlayer, Player secondPlayer,
				AttemptFactory attemptFactory) {
			return new Round(firstPlayer, secondPlayer, attemptFactory);
		}
	}
}
