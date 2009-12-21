package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AbstractGameFactory.InvalidGameArgumentsException;

public class RockPaperScissorsTest {

	private UsagePrinter mockUsagePrinter;
	private DefaultGameFactory mockDefaultGameFactory;
	private RockPaperScissors testObject;

	@Before
	public void setUp() {
		mockUsagePrinter = mock(UsagePrinter.class);
		mockDefaultGameFactory = mock(DefaultGameFactory.class);
		testObject = new RockPaperScissors(mockUsagePrinter,
				mockDefaultGameFactory);
	}

	@Test
	public void play_prints_usage_when_DefaultGameFactory_throws_InvalidGameArgumentsException()
			throws Exception {
		when(mockDefaultGameFactory.createGame(new String[0])).thenThrow(
				new InvalidGameArgumentsException());

		testObject.play(new String[0]);

		verify(mockUsagePrinter).printUsage();
	}
}
