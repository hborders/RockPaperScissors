package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToByGameFactoryFactoryTest extends AbstractGameFactoryFactoryTest {
	private ToByGameFactoryFactory testObject;

	@Override
	@Before
	public void setup() {
		super.setup();

		testObject = new ToByGameFactoryFactory(mockGameFactoryProvider,
				mockGameCountProvider);
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
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountProvider_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "", "-by", "foo" });
	}

	@Test
	public void createGame_returns_Game_from_GameProvider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameFactoryProvider.provide()).thenReturn(mockGameFactory);
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);

		GameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "", "-by", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}
}
