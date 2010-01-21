package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToByWonRoundCountFactoryTest {
	private ToByWonRoundCountFactory testObject;

	private RoundCount mockToRoundCount;
	private RoundCount mockByRoundCount;

	@Before
	public void setup() {

		testObject = new ToByWonRoundCountFactory();

		mockToRoundCount = mock(RoundCount.class);
		mockByRoundCount = mock(RoundCount.class);
	}

	@Test
	public void createWinningWonRoundCount_returns_WonRoundCount_with_by_RoundCount_when_by_is_greater_than_to() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(2);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(4);

		WonRoundCount winningWonRoundCount = testObject
				.createWinningWonRoundCount(mockToRoundCount, mockByRoundCount);

		assertEquals(new WonRoundCount(4), winningWonRoundCount);
	}

	@Test
	public void createWinningWonRoundCount_returns_WonRoundCount_with_to_RoundCount_when_to_is_greater_than_by() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(4);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(2);

		WonRoundCount winningWonRoundCount = testObject
				.createWinningWonRoundCount(mockToRoundCount, mockByRoundCount);

		assertEquals(new WonRoundCount(4), winningWonRoundCount);
	}

	@Test
	public void createExtendingWonRoundCount_returns_WonRoundCount_with_zero_when_by_is_greater_than_to() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(2);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(4);

		WonRoundCount extendingWonRoundCount = testObject
				.createExtendingWonRoundCount(mockToRoundCount,
						mockByRoundCount);

		assertEquals(new WonRoundCount(0), extendingWonRoundCount);
	}

	@Test
	public void createExtendingWonRoundCount_returns_WonRoundCount_with_to_minus_by_when_to_is_greater_than_by() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(5);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(2);

		WonRoundCount extendingWonRoundCount = testObject
				.createExtendingWonRoundCount(mockToRoundCount,
						mockByRoundCount);

		assertEquals(new WonRoundCount(3), extendingWonRoundCount);
	}
}
