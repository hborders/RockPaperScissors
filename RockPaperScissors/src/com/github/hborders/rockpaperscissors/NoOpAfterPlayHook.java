package com.github.hborders.rockpaperscissors;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;
import com.github.hborders.rockpaperscissors.Round.IAfterPlayHookFactory;

public class NoOpAfterPlayHook implements IAfterPlayHook {
	@Override
	public void afterPlay(Player winningPlayer) {
	}

	public static class NoOpAfterPlayHookFactory implements
			IAfterPlayHookFactory {
		@Override
		public IAfterPlayHook createAfterPlayHook(Player firstPlayer,
				Player secondPlayer) {
			return new NoOpAfterPlayHook();
		}
	}
}
