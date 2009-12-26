package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToByGameFactoryFactoryTest {
	private GameCountFactory mockGameCountFactory;
	private ToByWonRoundCountFactory mockToByWonRoundCountFactory;
	private Round.Provider mockRoundProvider;
	private AttemptReader mockAttemptReader;
	private ToByAfterPlayHook.Provider mockToByAfterPlayHookProvider;
	private GameFactory.Provider mockGameFactoryProvider;
	private Game.Provider mockGameProvider;

	private ToByGameFactoryFactory testObject;

	private GameCount mockByGameCount;
	private WonRoundCount mockWonRoundCount;
	private ToByAfterPlayHook mockToByAfterPlayHook;
	private Round mockRound;

	private GameCount mockToGameCount;

	private GameFactory mockToByGameFactory;

	@Before
	public void setup() {
		mockGameCountFactory = mock(GameCountFactory.class);
		mockToByWonRoundCountFactory = mock(ToByWonRoundCountFactory.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockAttemptReader = mock(AttemptReader.class);
		mockToByAfterPlayHookProvider = mock(ToByAfterPlayHook.Provider.class);
		mockGameFactoryProvider = mock(GameFactory.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new ToByGameFactoryFactory(mockGameCountFactory,
				mockToByWonRoundCountFactory, mockRoundProvider,
				mockAttemptReader, mockToByAfterPlayHookProvider,
				mockGameFactoryProvider, mockGameProvider);

		mockByGameCount = mock(GameCount.class);
		mockWonRoundCount = mock(WonRoundCount.class);
		mockToByAfterPlayHook = mock(ToByAfterPlayHook.class);
		mockRound = mock(Round.class);

		mockToGameCount = mock(GameCount.class);

		mockToByGameFactory = mock(GameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_3()
			throws Exception {
		testObject.createGameFactory(mockToGameCount, new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGameFactory(mockToGameCount, new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountFactory_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(mockToGameCount, new String[] { "", "",
				"-by", "foo" });
	}

	@Test
	public void createGame_returns_GameFactory_from_GameFactory_Provider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenReturn(
				mockByGameCount);
		when(
				mockToByWonRoundCountFactory.createWonRoundCount(
						mockToGameCount, mockByGameCount)).thenReturn(
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
				mockToGameCount, new String[] { "", "", "-by", "foo" });

		assertEquals(mockToByGameFactory, toByGameFactory);
	}
}
