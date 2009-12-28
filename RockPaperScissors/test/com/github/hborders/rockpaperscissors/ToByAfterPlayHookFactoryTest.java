package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToByAfterPlayHookFactoryTest {
	private ToByAfterPlayHook.Provider mockToByAfterPlayHookProvider;
	private WonRoundCount mockExtendingWonRoundCount;
	private WonRoundCount mockWinningWonRoundCount;

	private ToByAfterPlayHookFactory testObject;

	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	private ToByAfterPlayHook mockToByAfterPlayHook;

	@Before
	public void setup() {
		mockToByAfterPlayHookProvider = mock(ToByAfterPlayHook.Provider.class);
		mockExtendingWonRoundCount = mock(WonRoundCount.class);
		mockWinningWonRoundCount = mock(WonRoundCount.class);

		testObject = new ToByAfterPlayHookFactory(
				mockToByAfterPlayHookProvider, mockExtendingWonRoundCount,
				mockWinningWonRoundCount);

		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);

		mockToByAfterPlayHook = mock(ToByAfterPlayHook.class);
	}

	@Test
	public void createAfterPlayHook_returns_ToByAfterPlayHook() {
		when(
				mockToByAfterPlayHookProvider.provide(
						mockExtendingWonRoundCount, mockWinningWonRoundCount,
						mockFirstPlayer, mockSecondPlayer)).thenReturn(
				mockToByAfterPlayHook);

		ToByAfterPlayHook toByAfterPlayHook = testObject.createAfterPlayHook(
				mockFirstPlayer, mockSecondPlayer);

		assertEquals(mockToByAfterPlayHook, toByAfterPlayHook);
	}
}
