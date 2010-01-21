package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHookFactory;

public class ToByAfterPlayHookFactory implements IAfterPlayHookFactory {
	private final WonRoundCount extendingWonRoundCount;
	private final WonRoundCount winningWonRoundCount;

	public ToByAfterPlayHookFactory(WonRoundCount extendingWonRoundCount,
			WonRoundCount winningWonRoundCount) {
		this.extendingWonRoundCount = extendingWonRoundCount;
		this.winningWonRoundCount = winningWonRoundCount;
	}

	@Override
	public ToByAfterPlayHook createAfterPlayHook(Player firstPlayer,
			Player secondPlayer) {
		return new ToByAfterPlayHook(extendingWonRoundCount,
				winningWonRoundCount, firstPlayer, secondPlayer);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((extendingWonRoundCount == null) ? 0
						: extendingWonRoundCount.hashCode());
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
		ToByAfterPlayHookFactory other = (ToByAfterPlayHookFactory) obj;
		if (extendingWonRoundCount == null) {
			if (other.extendingWonRoundCount != null)
				return false;
		} else if (!extendingWonRoundCount.equals(other.extendingWonRoundCount))
			return false;
		if (winningWonRoundCount == null) {
			if (other.winningWonRoundCount != null)
				return false;
		} else if (!winningWonRoundCount.equals(other.winningWonRoundCount))
			return false;
		return true;
	}
}
