package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;

public class BestofGameFactoryFactoryTest {
	private BestofWonRoundCountFactory mockBestofWonRoundCountFactory;
	private BestofGameFactory.Provider mockBestofGameFactoryProvider;
	private BestofGame.Provider mockBestofGameProvider;
	private Round mockRound;

	private BestofGameFactoryFactory testObject;

	private WonRoundCount mockBestofWonRoundCount;
	private BestofGameFactory mockBestofGameFactory;

	@Before
	public void setup() {
		mockBestofWonRoundCountFactory = mock(BestofWonRoundCountFactory.class);
		mockBestofGameFactoryProvider = mock(BestofGameFactory.Provider.class);
		mockBestofGameProvider = mock(BestofGame.Provider.class);
		mockRound = mock(Round.class);

		testObject = new BestofGameFactoryFactory(
				mockBestofWonRoundCountFactory, mockBestofGameFactoryProvider,
				mockBestofGameProvider, mockRound);

		mockBestofWonRoundCount = mock(WonRoundCount.class);
		mockBestofGameFactory = mock(BestofGameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_not_2()
			throws Exception {
		testObject.createGameFactory(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_BestofWonRoundCountFactory_throws_InvalidWonRoundCountException()
			throws Exception {
		when(mockBestofWonRoundCountFactory.createWonRoundCount("foo"))
				.thenThrow(new InvalidWonRoundCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGameFactory_returns_BestofGameFactory_from_BestofGameFactoryProvider_when_args_length_is_2_and_BestofWonRoundCountFactory_returns_bestof_WonRoundCount()
			throws Exception {
		when(mockBestofWonRoundCountFactory.createWonRoundCount("foo"))
				.thenReturn(mockBestofWonRoundCount);
		when(
				mockBestofGameFactoryProvider.provide(mockBestofGameProvider,
						mockBestofWonRoundCount, mockRound)).thenReturn(
				mockBestofGameFactory);

		BestofGameFactory bestofGameFactory = testObject
				.createGameFactory(new String[] { "", "foo" });

		assertEquals(mockBestofGameFactory, bestofGameFactory);
	}
}
