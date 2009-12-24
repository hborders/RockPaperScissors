package com.github.hborders.rockpaperscissors;

public class BestofGame implements IGame {
	private final int gameCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private final Round.Provider roundProvider;

	public BestofGame(int gameCount, Player firstPlayer, Player secondPlayer) {
		this(gameCount, firstPlayer, secondPlayer, new Round.Provider());
	}

	BestofGame(int gameCount, Player firstPlayer, Player secondPlayer,
			Round.Provider roundProvider) {
		this.gameCount = gameCount;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.roundProvider = roundProvider;
	}

	@Override
	public Player play() {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public BestofGame provide(int gameCount, Player firstPlayer,
				Player secondPlayer, Round.Provider roundProvider) {
			return new BestofGame(gameCount, firstPlayer, secondPlayer,
					roundProvider);
		}
	}
}
