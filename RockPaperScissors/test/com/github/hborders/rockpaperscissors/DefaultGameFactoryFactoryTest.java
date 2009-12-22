package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;

public class DefaultGameFactoryFactoryTest extends
		AbstractGameFactoryFactoryTest {
	private ToGameFactoryFactory mockToGameFactoryFactory;
	private BestofGameFactoryFactory mockBestofGameFactoryFactory;

	private DefaultGameFactoryFactory testObject;

	@Override
	@Before
	public void setup() {
		super.setup();

		mockToGameFactoryFactory = mock(ToGameFactoryFactory.class);
		mockBestofGameFactoryFactory = mock(BestofGameFactoryFactory.class);

		testObject = new DefaultGameFactoryFactory(mockGameFactoryProvider,
				mockGameCountProvider, mockToGameFactoryFactory,
				mockBestofGameFactoryFactory);
	}

	@Test
	public void createGameFactory_returns_Game_from_GameProvider_when_args_is_empty()
			throws Exception {
		when(mockGameFactoryProvider.provide()).thenReturn(mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(new String[0]);

		assertEquals(mockGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToGameFactory_when_to_is_first_arg()
			throws Exception {
		String[] args = { "-to" };
		when(mockToGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockGameFactory, gameFactory);

		verify(mockToGameFactoryFactory).createGameFactory(args);
	}

	@Test
	public void createGame_delegates_to_BestofGameFactory_when_bestof_is_first_arg()
			throws Exception {
		String[] args = { "-bestof" };
		when(mockBestofGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockGameFactory);

		GameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockGameFactory, gameFactory);

		verify(mockBestofGameFactoryFactory).createGameFactory(args);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_first_arg_is_not_to_or_bestof()
			throws Exception {
		testObject.createGameFactory(new String[] { "foo" });
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_is_null()
			throws Exception {
		testObject.createGameFactory(null);
	}
}
