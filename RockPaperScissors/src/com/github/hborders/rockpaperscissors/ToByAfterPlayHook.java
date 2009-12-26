package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;

public class ToByAfterPlayHook implements IAfterPlayHook {

	private final WonRoundCount winningWonRoundCount;
	private Player lastPlayWinningPlayer;

	public ToByAfterPlayHook(WonRoundCount winningWonRoundCount) {
		this.winningWonRoundCount = winningWonRoundCount;
	}

	@Override
	public void afterPlay(Player winningPlayer) {
		if (lastPlayWinningPlayer == null) {
			lastPlayWinningPlayer = winningPlayer;
		} else {
			if (!winningPlayer.equals(lastPlayWinningPlayer)) {
				winningWonRoundCount.increment();
				lastPlayWinningPlayer = winningPlayer;
			}
		}
	}

}
