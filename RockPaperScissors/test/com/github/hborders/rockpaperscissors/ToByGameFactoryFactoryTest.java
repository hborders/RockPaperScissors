package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToByGameFactoryFactoryTest {
	private RoundCountFactory mockRoundCountFactory;
	private ToByWonRoundCountFactory mockToByWonRoundCountFactory;
	private ToByAfterPlayHookFactory.Provider mockToByAfterPlayHookFactoryProvider;
	private ToByAfterPlayHook.Provider mockToByAfterPlayHookProvider;
	private Round.Provider mockRoundProvider;
	private AttemptReader mockAttemptReader;
	private GameFactory.Provider mockGameFactoryProvider;
	private Game.Provider mockGameProvider;

	private ToByGameFactoryFactory testObject;

	private RoundCount mockToRoundCount;

	@Before
	public void setup() {
		mockRoundCountFactory = mock(RoundCountFactory.class);
		mockToByWonRoundCountFactory = mock(ToByWonRoundCountFactory.class);
		mockToByAfterPlayHookFactoryProvider = mock(ToByAfterPlayHookFactory.Provider.class);
		mockToByAfterPlayHookProvider = mock(ToByAfterPlayHook.Provider.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockAttemptReader = mock(AttemptReader.class);
		mockGameFactoryProvider = mock(GameFactory.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new ToByGameFactoryFactory(mockRoundCountFactory,
				mockToByWonRoundCountFactory,
				mockToByAfterPlayHookFactoryProvider,
				mockToByAfterPlayHookProvider, mockRoundProvider,
				mockAttemptReader, mockGameFactoryProvider, mockGameProvider);

		mockToRoundCount = mock(RoundCount.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_3()
			throws Exception {
		testObject.createGameFactory(mockToRoundCount, new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGameFactory(mockToRoundCount, new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_RoundCountFactory_throws_InvalidRoundCountException()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenThrow(
				new InvalidRoundCountException());

		testObject.createGameFactory(mockToRoundCount, new String[] { "", "",
				"-by", "foo" });
	}

	@Test
	public void createGameFactory_returns_GameFactory_from_GameFactory_Provider_when_RoundCount_Provider_returns_RoundCount()
			throws Exception {
		RoundCount mockByRoundCount = mock(RoundCount.class);
		when(mockRoundCountFactory.createRoundCount("foo")).thenReturn(
				mockByRoundCount);

		WonRoundCount mockWonRoundCount = mock(WonRoundCount.class);
		when(
				mockToByWonRoundCountFactory.createWonRoundCount(
						mockToRoundCount, mockByRoundCount)).thenReturn(
				mockWonRoundCount);

		ToByAfterPlayHookFactory mockToByAfterPlayHookFactory = mock(ToByAfterPlayHookFactory.class);
		when(
				mockToByAfterPlayHookFactoryProvider.provide(
						mockToByAfterPlayHookProvider, mockWonRoundCount))
				.thenReturn(mockToByAfterPlayHookFactory);

		GameFactory mockGameFactory = mock(GameFactory.class);
		when(
				mockGameFactoryProvider.provide(mockWonRoundCount,
						mockToByAfterPlayHookFactory, mockAttemptReader,
						mockRoundProvider, mockGameProvider)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(
				mockToRoundCount, new String[] { "", "", "-by", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}
}
