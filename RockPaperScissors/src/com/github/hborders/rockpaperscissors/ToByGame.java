package com.github.hborders.rockpaperscissors;

public class ToByGame extends AbstractGame {
	static class Provider {
		public ToByGame provide() {
			return new ToByGame();
		}
	}
}
