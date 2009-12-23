package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactoryFactory.InvalidGameArgumentsException;
import com.github.hborders.rockpaperscissors.GameCount.InvalidGameCountException;

public class ToByGameFactoryFactoryTest extends AbstractGameFactoryFactoryTest {
	private ToByGameFactory.Provider mockToByGameFactoryProvider;
	private ToByGame.Provider mockToByGameProvider;

	private ToByGameFactoryFactory testObject;

	private ToByGameFactory mockToByGameFactory;

	@Override
	@Before
	public void setup() {
		super.setup();

		mockToByGameFactoryProvider = mock(ToByGameFactory.Provider.class);
		mockToByGameProvider = mock(ToByGame.Provider.class);

		testObject = new ToByGameFactoryFactory(mockGameCountProvider,
				mockToByGameFactoryProvider, mockToByGameProvider);

		mockToByGameFactory = mock(ToByGameFactory.class);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_3()
			throws Exception {
		testObject.createGameFactory(new String[3]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_args_length_is_greater_than_4()
			throws Exception {
		testObject.createGameFactory(new String[5]);
	}

	@Test(expected = InvalidGameArgumentsException.class)
	public void createGame_throws_InvalidGameArgumentsException_when_GameCountProvider_throws_InvalidGameCountException()
			throws Exception {
		when(mockGameCountProvider.provide("foo")).thenThrow(
				new InvalidGameCountException());

		testObject.createGameFactory(new String[] { "", "", "-by", "foo" });
	}

	@Test
	public void createGame_returns_ToByGameFactory_from_ToByGameFactoryProvider_when_GameCountProvider_returns_GameCount()
			throws Exception {
		when(mockToByGameFactoryProvider.provide(mockToByGameProvider))
				.thenReturn(mockToByGameFactory);
		when(mockGameCountProvider.provide("foo")).thenReturn(mockGameCount);

		ToByGameFactory toByGameFactory = testObject
				.createGameFactory(new String[] { "", "", "-by", "foo" });

		assertEquals(mockToByGameFactory, toByGameFactory);
	}
}
