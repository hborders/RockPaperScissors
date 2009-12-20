package com.github.hborders.rockpaperscissors;

public class Game {

	static class Provider {
		public Game provide() {
			return new Game();
		}
	}
}
