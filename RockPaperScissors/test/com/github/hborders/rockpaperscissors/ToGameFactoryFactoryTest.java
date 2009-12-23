package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToGameFactoryFactoryTest extends AbstractGameFactoryFactoryTest {
	private ToGameFactory.Provider mockToGameFactoryProvider;
	private ToByGame.Provider mockToByGameProvider;
	private ToByGameFactoryFactory mockToByGameFactoryFactory;

	private ToGameFactoryFactory testObject;

	private ToGameFactory mockToGameFactory;
	private ToByGameFactory mockToByGameFactory;

	@Override
	@Before
	public void setup() {
		super.setup();

		mockToGameFactoryProvider = mock(ToGameFactory.Provider.class);
		mockToByGameProvider = mock(ToByGame.Provider.class);
		mockToByGameFactoryFactory = mock(ToByGameFactoryFactory.class);

		testObject = new ToGameFactoryFactory(mockGameCountProvider,
				mockToGameFactoryProvider, mockToByGameProvider,
				mockToByGameFactoryFactory);

		mockToGameFactory = mock(ToGameFactory.class);
		mockToByGameFactory = mock(ToByGameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_1()
			throws Exception {
		testObject.createGameFactory(new String[1]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountProvider_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "foo" });
	}

	@Test
	public void createGame_returns_ToGameFactory_from_ToGameFactoryProvider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);
		when(mockToGameFactoryProvider.provide(mockToByGameProvider))
				.thenReturn(mockToGameFactory);

		IGameFactory gameFactory = testObject.createGameFactory(new String[] {
				"", "foo" });

		assertEquals(mockToGameFactory, gameFactory);
	}

	@Test
	public void createGame_delegates_to_ToByGameFactoryFactory_when_3rd_argument_is_by()
			throws Exception {
		String[] args = { "", "", "-by" };
		when(mockToByGameFactoryFactory.createGameFactory(args)).thenReturn(
				mockToByGameFactory);
		IGameFactory gameFactory = testObject.createGameFactory(args);

		assertEquals(mockToByGameFactory, gameFactory);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_3rd_argument_is_not_by()
			throws Exception {
		testObject.createGameFactory(new String[] { "", "foo", "bar" });
	}
}
