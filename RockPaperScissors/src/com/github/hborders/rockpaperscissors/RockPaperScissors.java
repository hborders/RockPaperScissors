package com.github.hborders.rockpaperscissors;

public class RockPaperScissors {
	public static void main(String[] args) {
		new RockPaperScissors(new UsagePrinter()).play(args);
	}

	private final UsagePrinter usagePrinter;

	public RockPaperScissors(UsagePrinter usagePrinter) {
		this.usagePrinter = usagePrinter;
	}

	public void play(String[] args) {
		if (args.length == 0) {

		} else if (args.length == 2) {

		} else if (args.length == 4) {

		} else {
			usagePrinter.printUsage();
		}
	}
}
