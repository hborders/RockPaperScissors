package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class BestofGameFactoryTest {
	private Game.Provider mockGameProvider;
	private GameCount.Provider mockGameCountProvider;

	private BestofGameFactory testObject;

	private Game mockGame;

	@Before
	public void setup() {
		mockGameProvider = mock(Game.Provider.class);

		testObject = new BestofGameFactory(mockGameProvider,
				mockGameCountProvider);

		mockGame = mock(Game.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_not_2()
			throws Exception {
		testObject.createGame(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_arg_1_is_not_a_number()
			throws Exception {
		testObject.createGame(new String[] { "", "foo" });
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_arg_1_is_less_than_zero()
			throws Exception {
		testObject.createGame(new String[] { "", "0" });
	}

	public void createGame_returns_Game_from_GameProvider_when_args_length_is_2_and_arg_1_is_a_number_greater_than_zero()
			throws Exception {
		when(mockGameProvider.provide()).thenReturn(mockGame);

		Game game = testObject.createGame(new String[] { "", "1" });

		assertEquals(mockGameProvider, game);
	}
}
