package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;

public class AbstractGameFactoryFactoryTest {
	GameFactory.Provider mockGameFactoryProvider;
	GameCount.Provider mockGameCountProvider;

	GameFactory mockGameFactory;
	GameCount mockGameCount;

	@Before
	public void setup() {
		mockGameFactoryProvider = mock(GameFactory.Provider.class);
		mockGameCountProvider = mock(GameCount.Provider.class);

		mockGameFactory = mock(GameFactory.class);
		mockGameCount = mock(GameCount.class);
	}
}
