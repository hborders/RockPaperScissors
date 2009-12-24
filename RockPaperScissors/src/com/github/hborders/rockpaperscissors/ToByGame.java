package com.github.hborders.rockpaperscissors;

public class ToByGame implements IGame {

	@Override
	public Player play() {
		// TODO Auto-generated method stub
		return null;
	}

	static class Provider {
		public ToByGame provide() {
			return new ToByGame();
		}
	}
}
