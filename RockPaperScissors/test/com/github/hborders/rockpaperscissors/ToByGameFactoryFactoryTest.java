package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToByGameFactoryFactoryTest {
	private RoundCountFactory mockRoundCountFactory;
	private ToByWonRoundCountFactory mockToByWonRoundCountFactory;
	private Round.Provider mockRoundProvider;
	private AttemptReader mockAttemptReader;
	private ToByAfterPlayHook.Provider mockToByAfterPlayHookProvider;
	private GameFactory.Provider mockGameFactoryProvider;
	private Game.Provider mockGameProvider;

	private ToByGameFactoryFactory testObject;

	private RoundCount mockByRoundCount;
	private WonRoundCount mockWonRoundCount;
	private ToByAfterPlayHook mockToByAfterPlayHook;
	private Round mockRound;

	private RoundCount mockToRoundCount;

	private GameFactory mockToByGameFactory;

	@Before
	public void setup() {
		mockRoundCountFactory = mock(RoundCountFactory.class);
		mockToByWonRoundCountFactory = mock(ToByWonRoundCountFactory.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockAttemptReader = mock(AttemptReader.class);
		mockToByAfterPlayHookProvider = mock(ToByAfterPlayHook.Provider.class);
		mockGameFactoryProvider = mock(GameFactory.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new ToByGameFactoryFactory(mockRoundCountFactory,
				mockToByWonRoundCountFactory, mockRoundProvider,
				mockAttemptReader, mockToByAfterPlayHookProvider,
				mockGameFactoryProvider, mockGameProvider);

		mockByRoundCount = mock(RoundCount.class);
		mockWonRoundCount = mock(WonRoundCount.class);
		mockToByAfterPlayHook = mock(ToByAfterPlayHook.class);
		mockRound = mock(Round.class);

		mockToRoundCount = mock(RoundCount.class);

		mockToByGameFactory = mock(GameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_3()
			throws Exception {
		testObject.createGameFactory(mockToRoundCount, new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGameFactory(mockToRoundCount, new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_RoundCountFactory_throws_InvalidRoundCountException()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenThrow(
				new InvalidRoundCountException());

		testObject.createGameFactory(mockToRoundCount, new String[] { "", "",
				"-by", "foo" });
	}

	@Test
	public void createGame_returns_GameFactory_from_GameFactory_Provider_when_RoundCount_Provider_returns_RoundCount()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenReturn(
				mockByRoundCount);
		when(
				mockToByWonRoundCountFactory.createWonRoundCount(
						mockToRoundCount, mockByRoundCount)).thenReturn(
				mockWonRoundCount);
		when(mockToByAfterPlayHookProvider.provide(mockWonRoundCount))
				.thenReturn(mockToByAfterPlayHook);
		when(
				mockRoundProvider.provide(mockAttemptReader,
						mockToByAfterPlayHook)).thenReturn(mockRound);
		when(
				mockGameFactoryProvider.provide(mockWonRoundCount, mockRound,
						mockGameProvider)).thenReturn(mockToByGameFactory);

		GameFactory toByGameFactory = testObject.createGameFactory(
				mockToRoundCount, new String[] { "", "", "-by", "foo" });

		assertEquals(mockToByGameFactory, toByGameFactory);
	}
}
