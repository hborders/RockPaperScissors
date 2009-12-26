package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToByWonRoundCountFactoryTest {
	private RoundCount mockToRoundCount;
	private RoundCount mockByRoundCount;

	private ToByWonRoundCountFactory testObject;

	@Before
	public void setup() {
		mockToRoundCount = mock(RoundCount.class);
		mockByRoundCount = mock(RoundCount.class);

		testObject = new ToByWonRoundCountFactory();
	}

	@Test
	public void createWonRoundCount_adds_to_and_by_RoundCounts_and_subtracts_1() {
		when(mockToRoundCount.getRawRoundCount()).thenReturn(4);
		when(mockByRoundCount.getRawRoundCount()).thenReturn(2);

		WonRoundCount wonRoundCount = testObject.createWonRoundCount(
				mockToRoundCount, mockByRoundCount);

		assertTrue(wonRoundCount.matches(new WonRoundCount(5)));
	}
}
