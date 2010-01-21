package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToByAfterPlayHookFactoryTest {
	private WonRoundCount mockExtendingWonRoundCount;
	private WonRoundCount mockWinningWonRoundCount;

	private ToByAfterPlayHookFactory testObject;

	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	@Before
	public void setup() {
		mockExtendingWonRoundCount = mock(WonRoundCount.class);
		mockWinningWonRoundCount = mock(WonRoundCount.class);

		testObject = new ToByAfterPlayHookFactory(mockExtendingWonRoundCount,
				mockWinningWonRoundCount);

		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
	}

	@Test
	public void createAfterPlayHook_returns_ToByAfterPlayHook() {
		ToByAfterPlayHook toByAfterPlayHook = testObject.createAfterPlayHook(
				mockFirstPlayer, mockSecondPlayer);

		assertEquals(new ToByAfterPlayHook(mockExtendingWonRoundCount,
				mockWinningWonRoundCount, mockFirstPlayer, mockSecondPlayer),
				toByAfterPlayHook);
	}
}
