package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttemptTest {

	@Test
	public void rock_does_not_beat_rock() {
		assertFalse(Attempt.ROCK.beats(Attempt.ROCK));
	}

	@Test
	public void rock_does_not_beat_paper() {
		assertFalse(Attempt.ROCK.beats(Attempt.PAPER));
	}

	@Test
	public void rock_beats_scissors() {
		assertTrue(Attempt.ROCK.beats(Attempt.SCISSORS));
	}

	@Test
	public void scissors_does_not_beat_rock() {
		assertFalse(Attempt.SCISSORS.beats(Attempt.ROCK));
	}

	@Test
	public void scissors_beats_paper() {
		assertTrue(Attempt.SCISSORS.beats(Attempt.PAPER));
	}

	@Test
	public void scissors_does_not_beat_scissors() {
		assertFalse(Attempt.SCISSORS.beats(Attempt.SCISSORS));
	}

	@Test
	public void paper_beats_rock() {
		assertTrue(Attempt.PAPER.beats(Attempt.ROCK));
	}

	@Test
	public void paper_does_not_beat_scissors() {
		assertFalse(Attempt.PAPER.beats(Attempt.SCISSORS));
	}

	@Test
	public void paper_does_not_beat_paper() {
		assertFalse(Attempt.PAPER.beats(Attempt.PAPER));
	}
}
