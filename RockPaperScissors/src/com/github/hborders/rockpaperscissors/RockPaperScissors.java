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
			if (!"-to".equals(args[0]) || (new Integer(args[1]) < 1)) {
				usagePrinter.printUsage();
			}
		} else if (args.length == 4) {
			if (!"-to".equals(args[0]) || !"-by".equals(args[2])) {
				usagePrinter.printUsage();
			}
		} else {
			usagePrinter.printUsage();
		}
	}
}
