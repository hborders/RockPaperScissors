package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.NoOpAfterPlayHook.NoOpAfterPlayHookFactory;
import com.github.hborders.rockpaperscissors.RoundCountFactory.InvalidRoundCountException;

public class ToGameFactoryFactoryTest {
	private RoundCountFactory mockRoundCountFactory;
	private ToByGameFactoryFactory mockToByGameFactoryFactory;
	private ToWonRoundCountFactory mockToWonRoundCountFactory;
	private GameFactory.Provider mockGameFactoryProvider;
	private NoOpAfterPlayHookFactory mockNoOpAfterPlayHookFactory;
	private AttemptReader mockAttemptReader;
	private Round.Provider mockRoundProvider;
	private Game.Provider mockGameProvider;

	private ToGameFactoryFactory testObject;

	private RoundCount mockToRoundCount;
	private WonRoundCount mockWonRoundCount;
	private GameFactory mockGameFactory;

	@Before
	public void setup() {
		mockRoundCountFactory = mock(RoundCountFactory.class);
		mockToByGameFactoryFactory = mock(ToByGameFactoryFactory.class);
		mockToWonRoundCountFactory = mock(ToWonRoundCountFactory.class);
		mockGameFactoryProvider = mock(GameFactory.Provider.class);
		mockNoOpAfterPlayHookFactory = mock(NoOpAfterPlayHookFactory.class);
		mockAttemptReader = mock(AttemptReader.class);
		mockRoundProvider = mock(Round.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new ToGameFactoryFactory(mockRoundCountFactory,
				mockToByGameFactoryFactory, mockToWonRoundCountFactory,
				mockGameFactoryProvider, mockNoOpAfterPlayHookFactory,
				mockAttemptReader, mockRoundProvider, mockGameProvider);

		mockToRoundCount = mock(RoundCount.class);
		mockWonRoundCount = mock(WonRoundCount.class);
		mockGameFactory = mock(GameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_1()
			throws Exception {
		testObject.createGameFactory(new String[1]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_RoundCountFactory_throws_InvalidRoundCountException()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenThrow(
				new InvalidRoundCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGameFactory_returns_GameFactory_from_GameFactory_Provider_with_winning_WonRoundCount_from_RoundCount()
			throws Exception {
		when(mockRoundCountFactory.createRoundCount("foo")).thenReturn(
				mockToRoundCount);
		when(mockToWonRoundCountFactory.createWinningWonRoundCount(mockToRoundCount))
				.thenReturn(mockWonRoundCount);
		when(
				mockGameFactoryProvider.provide(mockWonRoundCount,
						mockNoOpAfterPlayHookFactory, mockAttemptReader,
						mockRoundProvider, mockGameProvider)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToByGameFactoryFactory_when_3rd_argument_is_by()
			throws Exception {
		String[] args = { "", "foo", "-by" };
		when(mockRoundCountFactory.createRoundCount("foo")).thenReturn(
				mockToRoundCount);
		when(
				mockToByGameFactoryFactory.createGameFactory(mockToRoundCount,
						args)).thenReturn(mockGameFactory);
		GameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockGameFactory, gameFactory);
	}
}
