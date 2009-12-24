package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.BestofGameFactory.InvalidGameCountException;

public class BestofGameFactoryTest {
	private BestofGame.Provider mockBestofGameProvider;

	@Before
	public void setup() {
		mockBestofGameProvider = mock(BestofGame.Provider.class);
	}

	@Test(expected = InvalidGameCountException.class)
	public void constructor_throws_InvalidGameCountException_when_gameCount_is_even()
			throws Exception {
		new BestofGameFactory(2, mockBestofGameProvider);
	}
}
