package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;

public class DefaultGameFactoryFactoryTest extends
		AbstractGameFactoryFactoryTest {
	private DefaultGameFactory.Provider mockDefaultGameFactoryProvider;
	private ToByGame.Provider mockToByGameProvider;
	private ToGameFactoryFactory mockToGameFactoryFactory;
	private BestofGameFactoryFactory mockBestofGameFactoryFactory;

	private DefaultGameFactoryFactory testObject;

	private DefaultGameFactory mockDefaultGameFactory;
	private ToByGameFactory mockToByGameFactory;
	private BestofGameFactory mockBestofGameFactory;

	@Override
	@Before
	public void setup() {
		super.setup();

		mockDefaultGameFactoryProvider = mock(DefaultGameFactory.Provider.class);
		mockToByGameProvider = mock(ToByGame.Provider.class);

		mockToGameFactoryFactory = mock(ToGameFactoryFactory.class);
		mockBestofGameFactoryFactory = mock(BestofGameFactoryFactory.class);

		testObject = new DefaultGameFactoryFactory(mockGameCountProvider,
				mockDefaultGameFactoryProvider, mockToByGameProvider,
				mockToGameFactoryFactory, mockBestofGameFactoryFactory);

		mockDefaultGameFactory = mock(DefaultGameFactory.class);
		mockToByGameFactory = mock(ToByGameFactory.class);
		mockBestofGameFactory = mock(BestofGameFactory.class);
	}

	@Test
	public void createGameFactory_returns_DefaultGameFactory_from_DefaultGameFactoryProvider_when_args_is_empty()
			throws Exception {
		when(mockDefaultGameFactoryProvider.provide(mockToByGameProvider))
				.thenReturn(mockDefaultGameFactory);

		IGameFactory gameFactory = testObject.createGameFactory(new String[0]);

		assertEquals(mockDefaultGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToGameFactoryFactory_when_to_is_first_arg()
			throws Exception {
		String[] args = { "-to" };
		when(mockToGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockToByGameFactory);

		IGameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockToByGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_BestofGameFactoryFactory_when_bestof_is_first_arg()
			throws Exception {
		String[] args = { "-bestof" };
		when(mockBestofGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockBestofGameFactory);

		IGameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockBestofGameFactory, gameFactory);
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