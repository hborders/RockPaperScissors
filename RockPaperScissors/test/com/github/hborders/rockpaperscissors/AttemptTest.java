package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttemptTest {

	@Test
	public void rock_does_not_beat_rock() {
		assertFalse(new Attempt("R").beats(new Attempt("R")));
	}

	@Test
	public void rock_does_not_beat_paper() {
		assertFalse(new Attempt("R").beats(new Attempt("P")));
	}

	@Test
	public void rock_beats_scissors() {
		assertTrue(new Attempt("R").beats(new Attempt("S")));
	}

	@Test
	public void scissors_does_not_beat_rock() {
		assertFalse(new Attempt("S").beats(new Attempt("R")));
	}

	@Test
	public void scissors_beats_paper() {
		assertTrue(new Attempt("S").beats(new Attempt("P")));
	}

	@Test
	public void scissors_does_not_beat_scissors() {
		assertFalse(new Attempt("S").beats(new Attempt("S")));
	}

	@Test
	public void paper_beats_rock() {
		assertTrue(new Attempt("P").beats(new Attempt("R")));
	}

	@Test
	public void paper_does_not_beat_scissors() {
		assertTrue(new Attempt("P").beats(new Attempt("S")));
	}

	@Test
	public void paper_does_not_beat_paper() {
		assertTrue(new Attempt("P").beats(new Attempt("P")));
	}

	@Test
	public void equality_is_case_insensitive() {
		assertEquals(new Attempt("R"), new Attempt("r"));
		assertEquals(new Attempt("P"), new Attempt("p"));
		assertEquals(new Attempt("S"), new Attempt("s"));

		assertEquals(new Attempt("R").hashCode(), new Attempt("r").hashCode());
		assertEquals(new Attempt("P").hashCode(), new Attempt("p").hashCode());
		assertEquals(new Attempt("S").hashCode(), new Attempt("s").hashCode());
	}
}
