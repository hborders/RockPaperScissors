package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;

public class NoOpAfterPlayHook implements IAfterPlayHook {
	@Override
	public void afterPlay(Player winningPlayer) {
	}
}
