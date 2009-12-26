package com.github.hborders.rockpaperscissors;

public interface IGameFactory {
	Game createGame(Player firstPlayer, Player secondPlayer);
}
