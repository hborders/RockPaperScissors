package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class DefaultGameFactoryTest {
	private Game.Provider mockGameProvider;
	private UsagePrinter mockUsagePrinter;
	private ToGameFactory mockToGameFactory;
	private BestofGameFactory mockBestofGameFactory;

	private DefaultGameFactory testObject;

	private Game mockGame;

	@Before
	public void setup() {
		mockToGameFactory = mock(ToGameFactory.class);
		mockBestofGameFactory = mock(BestofGameFactory.class);
		mockGameProvider = mock(Game.Provider.class);

		testObject = new DefaultGameFactory(mockGameProvider, mockUsagePrinter,
				mockToGameFactory, mockBestofGameFactory);

		mockGame = mock(Game.class);
	}

	@Test
	public void createGame_returns_Game_from_GameProvider_when_args_is_empty() {
		when(mockGameProvider.provide()).thenReturn(mockGame);

		Game game = testObject.createGame(new String[0]);

		assertEquals(mockGame, game);
	}
}
