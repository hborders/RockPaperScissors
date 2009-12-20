package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class DefaultGameFactoryTest {
	private Game.Provider mockGameProvider;
	private ToGameFactory mockToGameFactory;
	private BestofGameFactory mockBestofGameFactory;

	private DefaultGameFactory testObject;

	private Game mockGame;

	@Before
	public void setup() {
		mockToGameFactory = mock(ToGameFactory.class);
		mockBestofGameFactory = mock(BestofGameFactory.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new DefaultGameFactory(mockGameProvider,
				mockToGameFactory, mockBestofGameFactory);

		mockGame = mock(Game.class);
	}

	@Test
	public void createGame_returns_Game_from_GameProvider_when_args_is_empty()
			throws Exception {
		when(mockGameProvider.provide()).thenReturn(mockGame);

		Game game = testObject.createGame(new String[0]);

		assertEquals(mockGame, game);
	}

	@Test
	public void createGame_delegates_to_ToGameFactory_when_to_is_first_arg()
			throws Exception {
		String[] args = { "-to" };
		when(mockToGameFactory.createGame(args)).thenReturn(mockGame);

		Game game = testObject.createGame(args);

		assertEquals(mockGame, game);

		verify(mockToGameFactory).createGame(args);
	}

	@Test
	public void createGame_delegates_to_BestofGameFactory_when_bestof_is_first_arg()
			throws Exception {
		String[] args = { "-bestof" };
		when(mockBestofGameFactory.createGame(args)).thenReturn(mockGame);

		Game game = testObject.createGame(args);

		assertEquals(mockGame, game);

		verify(mockBestofGameFactory).createGame(args);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentException_when_first_arg_is_not_to_or_bestof()
			throws Exception {
		testObject.createGame(new String[] { "foo" });
	}
}
