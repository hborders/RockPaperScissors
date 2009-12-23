package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;

public class AbstractGameFactoryFactoryTest {
	GameCount.Provider mockGameCountProvider;

	GameCount mockGameCount;

	@Before
	public void setup() {
		mockGameCountProvider = mock(GameCount.Provider.class);

		mockGameCount = mock(GameCount.class);
	}
}
