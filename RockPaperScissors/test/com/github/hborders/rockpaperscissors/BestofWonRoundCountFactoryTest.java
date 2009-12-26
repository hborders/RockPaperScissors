package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofWonRoundCountFactoryTest {

	private BestofWonRoundCountFactory testObject;

	private RoundCount mockRoundCount;

	@Before
	public void setup() {
		testObject = new BestofWonRoundCountFactory();

		mockRoundCount = mock(RoundCount.class);
	}

	@Test(expected = InvalidWonRoundCountException.class)
	public void createWonRoundCount_throws_InvalidWonRoundCountException_when_RoundCount_is_even()
			throws Exception {
		when(mockRoundCount.getRoundCount()).thenReturn(2);

		testObject.createWonRoundCount(mockRoundCount);
	}

	@Test
	public void createWonRoundCount_returns_WonRoundCount_when_RoundCount_is_odd_number()
			throws Exception {
		when(mockRoundCount.getRoundCount()).thenReturn(1);

		WonRoundCount wonRoundCount = testObject
				.createWonRoundCount(mockRoundCount);

		assertTrue(wonRoundCount.matches(new WonRoundCount(1)));
	}
}
