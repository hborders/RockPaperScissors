package com.github.hborders.rockpaperscissors;

import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class ToByAfterPlayHookTest {
	private WonRoundCount mockExtendingWonRoundCount;
	private WonRoundCount mockWinningWonRoundCount;
	private Player mockFirstPlayer;
	private Player mockSecondPlayer;

	private ToByAfterPlayHook testObject;

	private WonRoundCount mockFirstPlayerWonRoundCount;
	private WonRoundCount mockSecondPlayerWonRoundCount;

	@Before
	public void setup() {
		mockExtendingWonRoundCount = mock(WonRoundCount.class);
		mockWinningWonRoundCount = mock(WonRoundCount.class);
		mockFirstPlayer = mock(Player.class);
		mockSecondPlayer = mock(Player.class);

		testObject = new ToByAfterPlayHook(mockExtendingWonRoundCount,
				mockWinningWonRoundCount, mockFirstPlayer, mockSecondPlayer);

		mockFirstPlayerWonRoundCount = mock(WonRoundCount.class);
		mockSecondPlayerWonRoundCount = mock(WonRoundCount.class);

		when(mockFirstPlayer.getWonRoundCount()).thenReturn(
				mockFirstPlayerWonRoundCount);
		when(mockSecondPlayer.getWonRoundCount()).thenReturn(
				mockSecondPlayerWonRoundCount);
	}

	@Test
	public void postRoundHook_increments_winning_WonRoundCount_when_leading_Player_loses_after_both_Players_beyond_extending_WonRoundCount()
			throws Exception {
		when(
				mockFirstPlayerWonRoundCount
						.compareTo(mockSecondPlayerWonRoundCount))
				.thenReturn(1).thenReturn(0);
		when(
				mockSecondPlayerWonRoundCount
						.compareTo(mockFirstPlayerWonRoundCount))
				.thenReturn(-1).thenReturn(0);

		when(mockFirstPlayerWonRoundCount.compareTo(mockExtendingWonRoundCount))
				.thenReturn(1);
		when(
				mockSecondPlayerWonRoundCount
						.compareTo(mockExtendingWonRoundCount)).thenReturn(1);

		testObject.afterPlay(mockFirstPlayer);
		testObject.afterPlay(mockSecondPlayer);

		verify(mockWinningWonRoundCount).increment();
	}

	@Test
	public void postRoundHook_does_not_increment_winning_WonRoundCount_when_there_is_no_leading_Player()
			throws Exception {
		when(
				mockFirstPlayerWonRoundCount
						.compareTo(mockSecondPlayerWonRoundCount))
				.thenReturn(1);
		when(
				mockSecondPlayerWonRoundCount
						.compareTo(mockFirstPlayerWonRoundCount))
				.thenReturn(-1);

		testObject.afterPlay(mockFirstPlayer);

		verify(mockWinningWonRoundCount, times(0)).increment();
	}

	@Test
	public void postRoundHook_does_not_increment_winning_WonRoundCount_when_leading_Player_wins()
			throws Exception {
		when(
				mockFirstPlayerWonRoundCount
						.compareTo(mockSecondPlayerWonRoundCount))
				.thenReturn(1).thenReturn(1);
		when(
				mockSecondPlayerWonRoundCount
						.compareTo(mockFirstPlayerWonRoundCount))
				.thenReturn(-1).thenReturn(-1);

		testObject.afterPlay(mockFirstPlayer);
		testObject.afterPlay(mockFirstPlayer);

		verify(mockWinningWonRoundCount, times(0)).increment();
	}

	@Test
	public void postRoundHook_does_not_increment_winning_WonRoundCount_when_leading_Player_loses_and_both_Players_not_beyond_extending_WonRoundCount()
			throws Exception {
		when(
				mockFirstPlayerWonRoundCount
						.compareTo(mockSecondPlayerWonRoundCount))
				.thenReturn(1).thenReturn(0);
		when(
				mockSecondPlayerWonRoundCount
						.compareTo(mockFirstPlayerWonRoundCount))
				.thenReturn(-1).thenReturn(0);

		when(mockFirstPlayerWonRoundCount.compareTo(mockExtendingWonRoundCount))
				.thenReturn(1);
		when(
				mockSecondPlayerWonRoundCount
						.compareTo(mockExtendingWonRoundCount)).thenReturn(-1);

		testObject.afterPlay(mockFirstPlayer);
		testObject.afterPlay(mockSecondPlayer);

		verify(mockWinningWonRoundCount, times(0)).increment();
	}
}
