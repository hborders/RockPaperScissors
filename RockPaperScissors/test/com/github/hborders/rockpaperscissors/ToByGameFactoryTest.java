package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class ToByGameFactoryTest {
	private Game.Provider mockGameProvider;

	private ToByGameFactory testObject;

	private Game mockGame;

	@Before
	public void setup() {
		mockGameProvider = mock(Game.Provider.class);

		testObject = new ToByGameFactory(mockGameProvider);

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
		testObject.createGame(new String[] { "", "", "-by", "" });
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGame(new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_by_is_not_a_number()
			throws Exception {
		testObject.createGame(new String[] { "", "", "", "foo" });
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_by_is_less_than_1()
			throws Exception {
		testObject.createGame(new String[] { "", "", "", "0" });
	}

	@Test
	public void createGame_returns_Game_from_GameProvider_when_by_is_greater_than_zero()
			throws Exception {
		when(mockGameProvider.provide()).thenReturn(mockGame);

		Game game = testObject.createGame(new String[] { "", "", "", "1" });

		assertEquals(mockGame, game);
	}
}
