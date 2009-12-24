package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.hborders.rockpaperscissors.AttemptFactory.InvalidRawAttemptException;

public class AttemptFactoryTest {
	private AttemptFactory testObject;

	@Before
	public void setup() {
		testObject = new AttemptFactory();
	}

	@Test
	public void createAttempt_returns_ROCK_Attempt_for_uppercase_r()
			throws Exception {
		Attempt attempt = testObject.createAttempt("R");

		assertEquals(Attempt.ROCK, attempt);
	}

	@Test
	public void createAttempt_returns_ROCK_Attempt_for_lowercase_r()
			throws Exception {
		Attempt attempt = testObject.createAttempt("r");

		assertEquals(Attempt.ROCK, attempt);
	}

	@Test
	public void createAttempt_returns_PAPER_Attempt_for_uppercase_p()
			throws Exception {
		Attempt attempt = testObject.createAttempt("P");

		assertEquals(Attempt.PAPER, attempt);
	}

	@Test
	public void createAttempt_returns_PAPER_Attempt_for_lowercase_p()
			throws Exception {
		Attempt attempt = testObject.createAttempt("p");

		assertEquals(Attempt.PAPER, attempt);
	}

	@Test
	public void createAttempt_returns_PAPER_Attempt_for_uppercase_s()
			throws Exception {
		Attempt attempt = testObject.createAttempt("S");

		assertEquals(Attempt.SCISSORS, attempt);
	}

	@Test
	public void createAttempt_returns_PAPER_Attempt_for_lowercase_s()
			throws Exception {
		Attempt attempt = testObject.createAttempt("s");

		assertEquals(Attempt.SCISSORS, attempt);
	}

	@Test(expected = InvalidRawAttemptException.class)
	public void createAttempt_throws_InvalidRawAttemptException_for_not_R_r_P_p_S_s()
			throws Exception {
		testObject.createAttempt("foo");
	}
}
