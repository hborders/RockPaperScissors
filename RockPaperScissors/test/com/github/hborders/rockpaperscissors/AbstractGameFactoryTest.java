package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;

public class AbstractGameFactoryTest {
	Game.Provider mockGameProvider;
	GameCount.Provider mockGameCountProvider;

	Game mockGame;
	GameCount mockGameCount;

	@Before
	public void setup() {
		mockGameProvider = mock(Game.Provider.class);
		mockGameCountProvider = mock(GameCount.Provider.class);

		mockGame = mock(Game.class);
		mockGameCount = mock(GameCount.class);
	}
}
