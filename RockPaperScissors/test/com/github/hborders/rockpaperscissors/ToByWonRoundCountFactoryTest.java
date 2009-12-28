package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToByWonRoundCountFactoryTest {
	private WonRoundCount.Provider mockWonRoundCountProvider;

	private ToByWonRoundCountFactory testObject;

	private RoundCount mockToRoundCount;
	private RoundCount mockByRoundCount;

	private WonRoundCount mockWonRoundCount;

	@Before
	public void setup() {
		mockWonRoundCountProvider = mock(WonRoundCount.Provider.class);

		testObject = new ToByWonRoundCountFactory(mockWonRoundCountProvider);

		mockToRoundCount = mock(RoundCount.class);
		mockByRoundCount = mock(RoundCount.class);

		mockWonRoundCount = mock(WonRoundCount.class);
	}

	@Test
	public void createWinningWonRoundCount_returns_WonRoundCount_with_by_RoundCount_when_by_is_greater_than_to() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(2);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(4);
		when(mockWonRoundCountProvider.provide(4))
				.thenReturn(mockWonRoundCount);

		WonRoundCount wonRoundCount = testObject.createWinningWonRoundCount(
				mockToRoundCount, mockByRoundCount);

		assertEquals(mockWonRoundCount, wonRoundCount);
	}

	@Test
	public void createWinningWonRoundCount_returns_WonRoundCount_with_to_RoundCount_when_to_is_greater_than_by() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(4);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(2);
		when(mockWonRoundCountProvider.provide(4))
				.thenReturn(mockWonRoundCount);

		WonRoundCount wonRoundCount = testObject.createWinningWonRoundCount(
				mockToRoundCount, mockByRoundCount);

		assertEquals(mockWonRoundCount, wonRoundCount);
	}

	@Test
	public void createExtendingWonRoundCount_returns_WonRoundCount_with_zero_when_by_is_greater_than_to() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(2);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(4);
		when(mockWonRoundCountProvider.provide(0))
				.thenReturn(mockWonRoundCount);

		WonRoundCount wonRoundCount = testObject.createExtendingWonRoundCount(
				mockToRoundCount, mockByRoundCount);

		assertEquals(mockWonRoundCount, wonRoundCount);
	}

	@Test
	public void createExtendingWonRoundCount_returns_WonRoundCount_with_to_minus_by_when_to_is_greater_than_by() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(5);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(2);
		when(mockWonRoundCountProvider.provide(3))
				.thenReturn(mockWonRoundCount);

		WonRoundCount wonRoundCount = testObject.createExtendingWonRoundCount(
				mockToRoundCount, mockByRoundCount);

		assertEquals(mockWonRoundCount, wonRoundCount);
	}
}
