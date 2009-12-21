package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class ToGameFactoryTest {
	private Game.Provider mockGameProvider;
	private ToByGameFactory mockToByGameFactory;

	private ToGameFactory testObject;

	private Game mockGame;

	@Before
	public void setup() {
		mockGameProvider = mock(Game.Provider.class);
		mockToByGameFactory = mock(ToByGameFactory.class);

		testObject = new ToGameFactory(mockGameProvider, new GameCount.Provider(), mockToByGameFactory);

		mockGame = mock(Game.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_1()
			throws Exception {
		assertEquals(null, null);
	}
}
