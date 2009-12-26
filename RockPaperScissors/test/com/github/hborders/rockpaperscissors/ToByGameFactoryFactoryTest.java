package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.GameCountFactory.InvalidGameCountException;

public class ToByGameFactoryFactoryTest {
	private GameCountFactory mockGameCountFactory;
	private ToByGameFactory.Provider mockToByGameFactoryProvider;
	private Game.Provider mockGameProvider;

	private ToByGameFactoryFactory testObject;

	private GameCount mockGameCount;
	private ToByGameFactory mockToByGameFactory;

	@Before
	public void setup() {
		mockGameCountFactory = mock(GameCountFactory.class);
		mockToByGameFactoryProvider = mock(ToByGameFactory.Provider.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new ToByGameFactoryFactory(mockGameCountFactory,
				mockToByGameFactoryProvider, mockGameProvider);

		mockGameCount = mock(GameCount.class);
		mockToByGameFactory = mock(ToByGameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_3()
			throws Exception {
		testObject.createGameFactory(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGameFactory(new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountFactory_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "", "-by", "foo" });
	}

	@Test
	public void createGame_returns_ToByGameFactory_from_ToByGameFactoryProvider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameCountFactory.createGameCount("foo")).thenReturn(
				mockGameCount);
		when(mockToByGameFactoryProvider.provide(mockGameProvider)).thenReturn(
				mockToByGameFactory);

		ToByGameFactory toByGameFactory = testObject
				.createGameFactory(new String[] { "", "", "-by", "foo" });

		assertEquals(mockToByGameFactory, toByGameFactory);
	}
}
