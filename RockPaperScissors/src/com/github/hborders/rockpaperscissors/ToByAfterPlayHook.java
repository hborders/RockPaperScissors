package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;

public class ToByAfterPlayHook implements IAfterPlayHook {

	private final WonRoundCount extendingWinninWonRoundCount;
	private final WonRoundCount winningWonRoundCount;
	private final Player firstPlayer;
	private final Player secondPlayer;
	private Player leadingPlayer;

	public ToByAfterPlayHook(WonRoundCount extendingWinninWonRoundCount,
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((extendingWinninWonRoundCount == null) ? 0
						: extendingWinninWonRoundCount.hashCode());
		result = prime * result
				+ ((firstPlayer == null) ? 0 : firstPlayer.hashCode());
		result = prime * result
				+ ((leadingPlayer == null) ? 0 : leadingPlayer.hashCode());
		result = prime * result
				+ ((secondPlayer == null) ? 0 : secondPlayer.hashCode());
		result = prime
				* result
				+ ((winningWonRoundCount == null) ? 0 : winningWonRoundCount
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToByAfterPlayHook other = (ToByAfterPlayHook) obj;
		if (extendingWinninWonRoundCount == null) {
			if (other.extendingWinninWonRoundCount != null)
				return false;
		} else if (!extendingWinninWonRoundCount
				.equals(other.extendingWinninWonRoundCount))
			return false;
		if (firstPlayer == null) {
			if (other.firstPlayer != null)
				return false;
		} else if (!firstPlayer.equals(other.firstPlayer))
			return false;
		if (leadingPlayer == null) {
			if (other.leadingPlayer != null)
				return false;
		} else if (!leadingPlayer.equals(other.leadingPlayer))
			return false;
		if (secondPlayer == null) {
			if (other.secondPlayer != null)
				return false;
		} else if (!secondPlayer.equals(other.secondPlayer))
			return false;
		if (winningWonRoundCount == null) {
			if (other.winningWonRoundCount != null)
				return false;
		} else if (!winningWonRoundCount.equals(other.winningWonRoundCount))
			return false;
		return true;
	}
}
