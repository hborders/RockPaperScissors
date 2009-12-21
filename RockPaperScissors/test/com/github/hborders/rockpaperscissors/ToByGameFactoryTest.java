package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToByGameFactoryTest extends AbstractGameFactoryTest {
	private ToByGameFactory testObject;

	private Game mockGame;

	@Override
	@Before
	public void setup() {
		super.setup();

		testObject = new ToByGameFactory(mockGameProvider,
				mockGameCountProvider);

		mockGame = mock(Game.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_3()
			throws Exception {
		testObject.createGame(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_3rd_arg_is_not_by()
			throws Exception {
		testObject.createGame(new String[] { "", "", "foo", "" });
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGame(new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountProvider_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGame(new String[] { "", "", "-by", "foo" });
	}

	@Test
	public void createGame_returns_Game_from_GameProvider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameProvider.provide()).thenReturn(mockGame);
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);

		Game game = testObject
				.createGame(new String[] { "", "", "-by", "foo" });

		assertEquals(mockGame, game);
	}
}
