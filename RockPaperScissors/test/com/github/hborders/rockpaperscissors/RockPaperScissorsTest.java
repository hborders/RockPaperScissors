package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class RockPaperScissorsTest {

	private UsagePrinter mockUsagePrinter;
	private RockPaperScissors testObject;

	@Before
	public void setUp() {
		mockUsagePrinter = mock(UsagePrinter.class);
		testObject = new RockPaperScissors(mockUsagePrinter);
	}

	@Test
	public void play_calls_UsagePrinter_when_arguments_length_is_1() {
		testObject.play(new String[1]);

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_arguments_length_is_3() {
		testObject.play(new String[3]);

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_arguments_length_is_greater_than_4() {
		testObject.play(new String[5]);

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_two_argument_array_first_argument_is_not_to_or_bestof() {
		testObject.play(new String[] { "foo", "bar" });

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_to_option_is_not_greater_than_zero() {
		testObject.play(new String[] { "-to", "0" });

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_bestof_option_is_not_a_number() {
		testObject.play(new String[] { "-bestof", "foo" });

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_bestof_option_is_not_greater_than_zero() {
		testObject.play(new String[] { "-bestof", "0" });

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_to_option_is_not_a_number() {
		testObject.play(new String[] { "-to", "foo" });

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_four_argument_array_first_argument_is_not_to_or_bestof() {
		testObject.play(new String[] { "foo", "bar", "foo", "foo" });

		verify(mockUsagePrinter).printUsage();
	}

	@Test
	public void play_calls_UsagePrinter_when_four_argument_array_third_argument_is_not_by() {
		testObject.play(new String[] { "-to", "bar", "foo", "foo" });

		verify(mockUsagePrinter).printUsage();
	}
}
