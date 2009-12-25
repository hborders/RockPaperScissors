package com.github.hborders.rockpaperscissors;

public class BestofGameFactory implements IGameFactory {
	private final BestofGame.Provider bestofGameProvider;
	private final WonRoundCount winningWonRoundCount;
	private final Round round;

	public BestofGameFactory(WonRoundCount winningWonRoundCount) {
		this(new BestofGame.Provider(), winningWonRoundCount, new Round());
	}

	BestofGameFactory(BestofGame.Provider bestofGameProvider,
			WonRoundCount winningWonRoundCount, Round round) {
		this.bestofGameProvider = bestofGameProvider;
		this.winningWonRoundCount = winningWonRoundCount;
		this.round = round;
	}

	@Override
	public BestofGame createGame(Player firstPlayer, Player secondPlayer) {
		return bestofGameProvider.provide(winningWonRoundCount, firstPlayer,
				secondPlayer, round);
	}

	static class Provider {
		BestofGameFactory provide(BestofGame.Provider bestofGameProvider,
				WonRoundCount winningWonRoundCount, Round round) {
			return new BestofGameFactory(bestofGameProvider,
					winningWonRoundCount, round);
		}
	}
}
