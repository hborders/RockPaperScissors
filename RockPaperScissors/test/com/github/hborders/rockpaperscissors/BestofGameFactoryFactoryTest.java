package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofWonRoundCountFactory.InvalidWonRoundCountException;
import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;

public class BestofGameFactoryFactoryTest {
	private RoundCountFactory mockRoundCountFactory;
	private BestofWonRoundCountFactory mockBestofWonRoundCountFactory;
	private GameFactory.Provider mockGameFactoryProvider;
	private NoOpAfterPlayHookFactory mockNoOpAfterPlayHookFactory;
	private AttemptReader mockAttemptReader;
	private Round.Provider mockRoundProvider;
	private Game.Provider mockGameProvider;

	private BestofGameFactoryFactory testObject;

	private RoundCount mockRoundCount;
	private WonRoundCount mockBestofWonRoundCount;
	private GameFactory mockGameFactory;

	@Before
	public void setup() {
		mockRoundCountFactory = mock(RoundCountFactory.class);
		mockBestofWonRoundCountFactory = mock(BestofWonRoundCountFactory.class);
		mockGameFactoryProvider = mock(GameFactory.Provider.class);
		mockNoOpAfterPlayHookFactory = mock(NoOpAfterPlayHookFactory.class);
		mockAttemptReader = mock(AttemptReader.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new BestofGameFactoryFactory(mockRoundCountFactory,
				mockBestofWonRoundCountFactory, mockGameFactoryProvider,
				mockNoOpAfterPlayHookFactory, mockAttemptReader,
				mockRoundProvider, mockGameProvider);

		mockRoundCount = mock(RoundCount.class);
		mockBestofWonRoundCount = mock(WonRoundCount.class);
		mockGameFactory = mock(GameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_not_2()
			throws Exception {
		testObject.createGameFactory(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_BestofWonRoundCountFactory_throws_InvalidWonRoundCountException()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenReturn(
				mockRoundCount);
		when(mockBestofWonRoundCountFactory.createWonRoundCount(mockRoundCount))
				.thenThrow(new InvalidWonRoundCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGameFactory_returns_GameFactory_from_GameFactory_Provider_when_args_length_is_2_and_BestofWonRoundCountFactory_returns_bestof_WonRoundCount()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenReturn(
				mockRoundCount);
		when(mockBestofWonRoundCountFactory.createWonRoundCount(mockRoundCount))
				.thenReturn(mockBestofWonRoundCount);
		when(
				mockGameFactoryProvider.provide(mockBestofWonRoundCount,
						mockNoOpAfterPlayHookFactory, mockAttemptReader,
						mockRoundProvider, mockGameProvider)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}
}
