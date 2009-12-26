package com.github.hborders.rockpaperscissors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.github.hborders.rockpaperscissors.Round.IAfterPlayHook;

public class RoundTest {
	private AttemptReader mockAttemptReader;
	private IAfterPlayHook mockAfterPlayHook;

	private Round testObject;

	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	private Attempt mockFirstPlayerAttempt;
	private Attempt mockSecondPlayerAttempt;

	private InOrder inOrder;

	@Before
	public void setup() {
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);
		mockAttemptReader = mock(AttemptReader.class);
		mockAfterPlayHook = mock(IAfterPlayHook.class);

		testObject = new Round(mockAttemptReader, mockAfterPlayHook);

		mockFirstPlayerAttempt = mock(Attempt.class);
		mockSecondPlayerAttempt = mock(Attempt.class);

		inOrder = inOrder(mockFirstPlayer, mockSecondPlayer, mockAfterPlayHook);
	}

	@Test
	public void play_creates_Attemptfor_firstPlayer_then_secondPlayer_and_wonRounds_first_Player_and_postRoundHooks_with_first_Player_when_first_Player_wins()
			throws Exception {
		when(mockAttemptReader.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt);
		when(mockAttemptReader.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.TRUE);

		Player winningPlayer = testObject.play(mockFirstPlayer,
				mockSecondPlayer);

		inOrder.verify(mockFirstPlayer).wonRound();
		inOrder.verify(mockAfterPlayHook).afterPlay(mockFirstPlayer);

		assertEquals(mockFirstPlayer, winningPlayer);
	}

	@Test
	public void play_creates_Attempt_for_firstPlayer_then_secondPlayer_and_wonRounds_and_postRoundHooks_with_second_Player_when_second_Player_wins()
			throws Exception {
		when(mockAttemptReader.createAttempt(mockFirstPlayer)).thenReturn(
				mockFirstPlayerAttempt);
		when(mockAttemptReader.createAttempt(mockSecondPlayer)).thenReturn(
				mockSecondPlayerAttempt);
		when(mockFirstPlayerAttempt.beats(mockSecondPlayerAttempt)).thenReturn(
				Boolean.FALSE);
		when(mockSecondPlayerAttempt.beats(mockFirstPlayerAttempt)).thenReturn(
				Boolean.TRUE);

		Player winningPlayer = testObject.play(mockFirstPlayer,
				mockSecondPlayer);

		inOrder.verify(mockSecondPlayer).wonRound();
		inOrder.verify(mockAfterPlayHook).afterPlay(mockSecondPlayer);

		assertEquals(mockSecondPlayer, winningPlayer);
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

		Player winningPlayer = testObject.play(mockFirstPlayer,
				mockSecondPlayer);

		inOrder.verify(mockFirstPlayer).wonRound();
		inOrder.verify(mockAfterPlayHook).afterPlay(mockFirstPlayer);

		assertEquals(mockFirstPlayer, winningPlayer);
	}
}
