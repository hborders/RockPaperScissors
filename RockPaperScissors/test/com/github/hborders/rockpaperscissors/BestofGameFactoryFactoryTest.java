package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class BestofGameFactoryFactoryTest extends
		AbstractGameFactoryFactoryTest {
	private BestofGameFactory.Provider mockBestofGameFactoryProvider;
	private BestofGame.Provider mockBestofGameProvider;
	private BestofGameFactoryFactory testObject;

	private BestofGameFactory mockBestofGameFactory;

	@Override
	@Before
	public void setup() {
		super.setup();

		mockBestofGameFactoryProvider = mock(BestofGameFactory.Provider.class);
		mockBestofGameProvider = mock(BestofGame.Provider.class);

		testObject = new BestofGameFactoryFactory(mockGameCountProvider,
				mockBestofGameFactoryProvider, mockBestofGameProvider);

		mockBestofGameFactory = mock(BestofGameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_args_length_is_not_2()
			throws Exception {
		testObject.createGameFactory(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGameFactory_throws_InvalidGameArgumentsException_when_GameCountProvider_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	public void createGameFactory_returns_BestofGameFactory_from_BestofGameFactoryProvider_when_args_length_is_2_and_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockBestofGameFactoryProvider.provide(mockBestofGameProvider))
				.thenReturn(mockBestofGameFactory);
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);

		BestofGameFactory bestofGameFactory = testObject
				.createGameFactory(new String[] { "", "foo" });

		assertEquals(mockBestofGameFactory, bestofGameFactory);
	}
}