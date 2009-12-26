package com.github.hborders.rockpaperscissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class RockPaperScissors {
	public static void main(String[] args) {
		RockPaperScissors rockPaperScissors = new RockPaperScissors(System.in,
				System.out);
		rockPaperScissors.play(args);
	}

	private final UsagePrinter usagePrinter;
	private final DefaultGameFactoryFactory defaultGameFactoryFactory;
	private final PlayerFactory playerFactory;

	public RockPaperScissors(InputStream inputStream, OutputStream outputStream) {
		this(createDefaultGameFactoryFactoryAndPlayerFactory(inputStream,
				outputStream));
	}

	private RockPaperScissors(Object[] defaultGameFactoryFactoryAndPlayerFactory) {
		this(
				new UsagePrinter(),
				(DefaultGameFactoryFactory) defaultGameFactoryFactoryAndPlayerFactory[0],
				(PlayerFactory) defaultGameFactoryFactoryAndPlayerFactory[1]);
	}

	RockPaperScissors(UsagePrinter usagePrinter,
			DefaultGameFactoryFactory defaultGameFactoryFactory,
			PlayerFactory playerFactory) {
		this.usagePrinter = usagePrinter;
		this.defaultGameFactoryFactory = defaultGameFactoryFactory;
		this.playerFactory = playerFactory;
	}

	private static Object[] createDefaultGameFactoryFactoryAndPlayerFactory(
			InputStream inputStream, OutputStream outputStream) {
		GameFactory.Provider gameFactoryProvider = new GameFactory.Provider();
		BufferedReader systemInBufferedReader = new BufferedReader(
				new InputStreamReader(System.in));
		PrintWriter systemOutPrintWriter = new PrintWriter(System.out);
		AttemptFactory attemptFactory = new AttemptFactory();
		AttemptReader attemptReader = new AttemptReader(systemInBufferedReader,
				systemOutPrintWriter, attemptFactory);
		Round defaultRound = new Round(attemptReader, new NoOpAfterPlayHook());
		Game.Provider gameProvider = new Game.Provider();

		RoundCountFactory roundCountFactory = new RoundCountFactory();
		ToByWonRoundCountFactory toByWonRoundCountFactory = new ToByWonRoundCountFactory();
		Round.Provider roundProvider = new Round.Provider();
		ToByAfterPlayHook.Provider toByAfterPlayHookProvider = new ToByAfterPlayHook.Provider();
		ToByGameFactoryFactory toByGameFactoryFactory = new ToByGameFactoryFactory(
				roundCountFactory, toByWonRoundCountFactory, roundProvider,
				attemptReader, toByAfterPlayHookProvider, gameFactoryProvider,
				gameProvider);

		ToWonRoundCountFactory toWonRoundCountFactory = new ToWonRoundCountFactory();
		ToGameFactoryFactory toGameFactoryFactory = new ToGameFactoryFactory(
				roundCountFactory, toWonRoundCountFactory,
				toByGameFactoryFactory, gameProvider, defaultRound,
				gameFactoryProvider);

		BestofWonRoundCountFactory bestofWonRoundCountFactory = new BestofWonRoundCountFactory();
		BestofGameFactoryFactory bestofGameFactoryFactory = new BestofGameFactoryFactory(
				roundCountFactory, bestofWonRoundCountFactory,
				gameFactoryProvider, gameProvider, defaultRound);

		WonRoundCount defaultWinningWonRoundCount = new WonRoundCount(1);
		DefaultGameFactoryFactory defaultGameFactoryFactory = new DefaultGameFactoryFactory(
				gameFactoryProvider, defaultRound, defaultWinningWonRoundCount,
				gameProvider, toGameFactoryFactory, bestofGameFactoryFactory);

		WonRoundCount.Provider wonRoundCountProvider = new WonRoundCount.Provider();
		Player.Provider playerProvider = new Player.Provider();
		PlayerFactory playerFactory = new PlayerFactory(systemInBufferedReader,
				systemOutPrintWriter, wonRoundCountProvider, playerProvider);

		return new Object[] { defaultGameFactoryFactory, playerFactory };
	}

	public void play(String[] args) {
		try {
			GameFactory gameFactory = defaultGameFactoryFactory
					.createGameFactory(args);
			Player firstPlayer = playerFactory.createPlayer(1);
			Player secondPlayer = playerFactory.createPlayer(2);
			Game game = gameFactory.createGame(firstPlayer, secondPlayer);
			game.play();
		} catch (InvalidGameArgumentsException invalidGameArgumentsException) {
			usagePrinter.printUsage();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
