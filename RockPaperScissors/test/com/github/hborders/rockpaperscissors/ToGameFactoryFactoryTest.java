package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToGameFactoryFactoryTest extends AbstractGameFactoryFactoryTest {
	private ToByGameFactoryFactory mockToByGameFactoryFactory;

	private ToGameFactoryFactory testObject;

	@Override
	@Before
	public void setup() {
		super.setup();

		mockToByGameFactoryFactory = mock(ToByGameFactoryFactory.class);

		testObject = new ToGameFactoryFactory(mockGameFactoryProvider,
				mockGameCountProvider, mockToByGameFactoryFactory);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_1()
			throws Exception {
		testObject.createGameFactory(new String[1]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountProvider_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGame_returns_Game_from_GameProvider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);
		when(mockGameFactoryProvider.provide()).thenReturn(mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToByGameFactory_when_3rd_argument_is_by()
			throws Exception {
		String[] args = { "", "", "-by" };
		when(mockToByGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockGameFactory);
		GameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_3rd_argument_is_not_by()
			throws Exception {
		testObject.createGameFactory(new String[] { "", "foo", "bar" });
	}
}
