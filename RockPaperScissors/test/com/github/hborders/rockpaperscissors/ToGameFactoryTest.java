package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToGameFactoryTest extends AbstractGameFactoryTest {
	private ToByGameFactory mockToByGameFactory;

	private ToGameFactory testObject;

	@Override
	@Before
	public void setup() {
		super.setup();

		mockToByGameFactory = mock(ToByGameFactory.class);

		testObject = new ToGameFactory(mockGameProvider, mockGameCountProvider,
				mockToByGameFactory);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_1()
			throws Exception {
		testObject.createGame(new String[1]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountProvider_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGame(new String[] { "", "foo" });
	}

	@Test
	public void createGame_returns_Game_from_GameProvider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);
		when(mockGameProvider.provide()).thenReturn(mockGame);

		Game game = testObject.createGame(new String[] { "", "foo" });

		assertEquals(mockGame, game);
	}

	@Test
	public void createGame_delegates_to_ToByGameFactory_when_3rd_argument_is_by()
			throws Exception {
		String[] args = { "", "", "-by" };
		when(mockToByGameFactory.createGame(args)).thenReturn(mockGame);
		Game game = testObject.createGame(args);

		assertEquals(mockGame, game);
	}

	@Test
	public void createGame_throws_InvalidGameArgumentsException_when_3rd_argument_is_not_by()
			throws Exception {
		testObject.createGame(new String[] { "", "foo", "bar" });
	}
}
