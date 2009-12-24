package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class RoundTest {
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;
	private AttemptReader mockAttemptReader;

	private Round testObject;

	private Attempt mockFirstPlayerAttempt;
	private Attempt mockSecondPlayerAttempt;

	@Before
	public void setup() {
		mockAttemptReader = mock(AttemptReader.class);

		testObject = new Round(mockAttemptReader);

		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);

		mockFirstPlayerAttempt = mock(Attempt.class);
		mockSecondPlayerAttempt = mock(Attempt.class);
	}

	@Test
	public void play_creates_Attemptfor_firstPlayer_then_secondPlayer_and_wonGames_firstPlayer_when_firstPlayer_wins()
			throws Exception {
		when(mockAttemptReader.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt);
		when(mockAttemptReader.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.TRUE);

		testObject.play(mockFirstPlayer, mockSecondPlayer);

		verify(mockFirstPlayer).wonGame();
	}

	@Test
	public void play_creates_Attemptfor_firstPlayer_then_secondPlayer_and_wonGames_secondPlayer_when_secondPlayer_wins()
			throws Exception {
		when(mockAttemptReader.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt);
		when(mockAttemptReader.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.FALSE);
		when(mockSecondPlayerAttempt.beats(mockFirstPlayerAttempt)).thenReturn(
				Boolean.TRUE);

		testObject.play(mockFirstPlayer, mockSecondPlayer);

		verify(mockSecondPlayer).wonGame();
	}

	@Test
	public void play_creates_new_Attempts_and_evaluates_new_Attempts_in_case_of_a_tie()
			throws Exception {
		Attempt mockFirstPlayerSecondAttempt = mock(Attempt.class);
		when(mockAttemptReader.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt)
				.thenReturn(mockFirstPlayerSecondAttempt);
		Attempt mockSecondPlayerSecondAttempt = mock(Attempt.class);
		when(mockAttemptReader.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt).thenReturn(
				mockSecondPlayerSecondAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.FALSE);
		when(mockSecondPlayerAttempt.beats(mockFirstPlayerAttempt)).thenReturn(
				Boolean.FALSE);
		when(mockFirstPlayerSecondAttempt.beats(mockSecondPlayerSecondAttempt))
				.thenReturn(Boolean.TRUE);

		testObject.play(mockFirstPlayer, mockSecondPlayer);

		verify(mockFirstPlayer).wonGame();
	}
}
