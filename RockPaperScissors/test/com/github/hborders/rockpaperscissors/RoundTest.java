package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class RoundTest {
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private AttemptFactory mockAttemptFactory;

	private Round testObject;

	private Attempt mockFirstPlayerAttempt;
	private Attempt mockSecondPlayerAttempt;

	@Before
	public void setup() {
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockAttemptFactory = mock(AttemptFactory.class);

		testObject = new Round(mockFirstPlayer, mockSecondPlayer,
				mockAttemptFactory);

		mockFirstPlayerAttempt = mock(Attempt.class);
		mockSecondPlayerAttempt = mock(Attempt.class);
	}

	@Test
	public void play_creates_Attemptfor_firstPlayer_then_secondPlayer_and_returns_firstPlayer_when_firstPlayer_wins() {
		when(mockAttemptFactory.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt);
		when(mockAttemptFactory.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.TRUE);

		Player winningPlayer = testObject.play();

		assertEquals(mockFirstPlayer, winningPlayer);
	}

	@Test
	public void play_creates_Attemptfor_firstPlayer_then_secondPlayer_and_returns_secondPlayer_when_secondPlayer_wins() {
		when(mockAttemptFactory.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt);
		when(mockAttemptFactory.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.FALSE);
		when(mockSecondPlayerAttempt.beats(mockFirstPlayerAttempt)).thenReturn(
				Boolean.TRUE);

		Player winningPlayer = testObject.play();

		assertEquals(mockSecondPlayer, winningPlayer);
	}

	@Test
	public void play_creates_new_Attempts_and_evaluates_new_Attempts_in_case_of_a_tie() {
		Attempt mockFirstPlayerSecondAttempt = mock(Attempt.class);
		when(mockAttemptFactory.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt)
				.thenReturn(mockFirstPlayerSecondAttempt);
		Attempt mockSecondPlayerSecondAttempt = mock(Attempt.class);
		when(mockAttemptFactory.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt).thenReturn(
				mockSecondPlayerSecondAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.FALSE);
		when(mockSecondPlayerAttempt.beats(mockFirstPlayerAttempt)).thenReturn(
				Boolean.FALSE);
		when(mockFirstPlayerSecondAttempt.beats(mockSecondPlayerSecondAttempt))
				.thenReturn(Boolean.TRUE);
		Player winningPlayer = testObject.play();

		assertEquals(mockFirstPlayer, winningPlayer);
	}
}
