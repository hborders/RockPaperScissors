package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;

public class ToByAfterPlayHook implements IAfterPlayHook {

	private final WonRoundCount extendingWinninWonRoundCount;
	private final WonRoundCount winningWonRoundCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private Player leadingPlayer;

	ToByAfterPlayHook(WonRoundCount extendingWinninWonRoundCount,
			WonRoundCount winningWonRoundCount, Player firstPlayer,
			Player secondPlayer) {
		this.extendingWinninWonRoundCount = extendingWinninWonRoundCount;
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
				WonRoundCount firstPlayerWonRoundCount = firstPlayer
						.getWonRoundCount();
				WonRoundCount secondPlayerWonRoundCount = secondPlayer
						.getWonRoundCount();
				if ((0 < firstPlayerWonRoundCount
						.compareTo(extendingWinninWonRoundCount))
						&& (0 < secondPlayerWonRoundCount
								.compareTo(extendingWinninWonRoundCount))) {
					winningWonRoundCount.increment();
				}

				int comparison = firstPlayerWonRoundCount
						.compareTo(secondPlayerWonRoundCount);
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
		public ToByAfterPlayHook provide(
				WonRoundCount extendingWinninWonRoundCount,
				WonRoundCount winningWonRoundCount, Player firstPlayer,
				Player secondPlayer) {
			return new ToByAfterPlayHook(extendingWinninWonRoundCount,
					winningWonRoundCount, firstPlayer, secondPlayer);
		}
	}
}
