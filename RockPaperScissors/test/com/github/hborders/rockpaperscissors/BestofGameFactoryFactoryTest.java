package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class BestofGameFactoryFactoryTest extends
		AbstractGameFactoryFactoryTest {
	private BestofGameFactoryFactory testObject;

	@Override
	@Before
	public void setup() {
		super.setup();

		testObject = new BestofGameFactoryFactory(mockGameFactoryProvider,
				mockGameCountProvider);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_not_2()
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

	public void createGameFactory_returns_GameFactory_from_GameFactoryProvider_when_args_length_is_2_and_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameFactoryProvider.provide()).thenReturn(mockGameFactory);
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);

		GameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockGameFactory, gameFactory);
	}
}
