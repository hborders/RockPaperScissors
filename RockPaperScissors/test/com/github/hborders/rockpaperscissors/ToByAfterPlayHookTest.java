package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToByAfterPlayHookTest {
	private WonRoundCount mockWinningWonRoundCount;

	private ToByAfterPlayHook testObject;

	private Player mockFirstPlayWinningPlayer;
	private Player mockSecondPlayWinningPlayer;

	@Before
	public void setup() {
		mockWinningWonRoundCount = mock(WonRoundCount.class);

		testObject = new ToByAfterPlayHook(mockWinningWonRoundCount);

		mockFirstPlayWinningPlayer = mock(Player.class);
		mockSecondPlayWinningPlayer = mock(Player.class);
	}

	@Test
	public void postRoundHook_increments_winningRoundCount_when_winning_Player_is_different_for_first_and_second_plays()
			throws Exception {
		testObject.afterPlay(mockFirstPlayWinningPlayer);
		testObject.afterPlay(mockSecondPlayWinningPlayer);

		verify(mockWinningWonRoundCount).increment();
	}
}
