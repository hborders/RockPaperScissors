package com.github.hborders.rockpaperscissors;

public class GameFactory {
	static class Provider {
		public GameFactory provide() {
			return new GameFactory();
		}
	}
}
