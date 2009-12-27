package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;

public class ToByAfterPlayHook implements IAfterPlayHook {

	private final WonRoundCount winningWonRoundCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private Player leadingPlayer;

	ToByAfterPlayHook(WonRoundCount winningWonRoundCount, Player firstPlayer,
			Player secondPlayer) {
		this.winningWonRoundCount = winningWonRoundCount;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
	}

	@Override
	public void afterPlay(Player winningPlayer) {
		if (leadingPlayer == null) {
			leadingPlayer = winningPlayer;
		} else {
			if (!winningPlayer.equals(leadingPlayer)) {
				winningWonRoundCount.increment();

				int comparison = firstPlayer.getWonRoundCount().compareTo(
						secondPlayer.getWonRoundCount());
				if (comparison < 0) {
					leadingPlayer = secondPlayer;
				} else if (0 == comparison) {
					leadingPlayer = null;
				} else {
					leadingPlayer = firstPlayer;
				}
			}
		}
	}

	public static class Provider {
		public ToByAfterPlayHook provide(WonRoundCount winningWonRoundCount,
				Player firstPlayer, Player secondPlayer) {
			return new ToByAfterPlayHook(winningWonRoundCount, firstPlayer,
					secondPlayer);
		}
	}
}
