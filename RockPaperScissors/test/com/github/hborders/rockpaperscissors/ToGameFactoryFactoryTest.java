package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToGameFactoryFactoryTest {
	private GameCountFactory mockGameCountFactory;
	private ToGameFactory.Provider mockToGameFactoryProvider;
	private Game.Provider mockGameProvider;
	private ToByGameFactoryFactory mockToByGameFactoryFactory;

	private ToGameFactoryFactory testObject;

	private GameCount mockGameCount;
	private ToGameFactory mockToGameFactory;
	private ToByGameFactory mockToByGameFactory;

	@Before
	public void setup() {
		mockGameCountFactory = mock(GameCountFactory.class);
		mockToGameFactoryProvider = mock(ToGameFactory.Provider.class);
		mockGameProvider = mock(Game.Provider.class);
		mockToByGameFactoryFactory = mock(ToByGameFactoryFactory.class);

		testObject = new ToGameFactoryFactory(mockGameCountFactory,
				mockToGameFactoryProvider, mockGameProvider,
				mockToByGameFactoryFactory);

		mockGameCount = mock(GameCount.class);
		mockToGameFactory = mock(ToGameFactory.class);
		mockToByGameFactory = mock(ToByGameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_1()
			throws Exception {
		testObject.createGameFactory(new String[1]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountFactory_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGame_returns_ToGameFactory_from_ToGameFactoryProvider_when_GameCountFactory_returns_GameCount()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenReturn(
				mockGameCount);
		when(mockToGameFactoryProvider.provide(mockGameProvider)).thenReturn(
				mockToGameFactory);

		IGameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockToGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToByGameFactoryFactory_when_3rd_argument_is_by()
			throws Exception {
		String[] args = { "", "", "-by" };
		when(mockToByGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockToByGameFactory);
		IGameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockToByGameFactory, gameFactory);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_3rd_argument_is_not_by()
			throws Exception {
		testObject.createGameFactory(new String[] { "", "foo", "bar" });
	}
}
