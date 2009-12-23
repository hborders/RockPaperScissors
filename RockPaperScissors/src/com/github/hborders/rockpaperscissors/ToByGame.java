package com.github.hborders.rockpaperscissors;

public class ToByGame implements IGame {

	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	static class Provider {
		public ToByGame provide() {
			return new ToByGame();
		}
	}
}
