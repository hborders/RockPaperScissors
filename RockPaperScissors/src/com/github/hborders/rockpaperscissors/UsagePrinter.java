package com.github.hborders.rockpaperscissors;

public class UsagePrinter {
	public void printUsage() {
		System.out.println("Usage:");
		System.out.println("java RockPaperScissors");
		System.out.println("java RockPaperScissors -to <win count>");
		System.out.println("java RockPaperScissors -to <win count> -by <win margin>");
		System.out.println("java RockPaperScissors -bestof <game count>");
	}
}
