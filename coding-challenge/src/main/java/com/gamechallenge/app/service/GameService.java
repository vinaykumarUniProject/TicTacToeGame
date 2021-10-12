package com.gamechallenge.app.service;

import com.gamechallenge.app.model.GameChart;
import com.gamechallenge.app.model.GamePlay;
import com.gamechallenge.app.model.Player;

public interface GameService {

	/**
	 * This method places a player's pawn in the desired position
	 * 
	 * @param @GamePlay
	 * @returns @GameChart
	 */
	public GameChart placePawn(GamePlay p);

	/**
	 * This method resets the Game chart
	 * 
	 * @returns @GameChart
	 */
	public GameChart resetGame();

	/**
	 * This method registers a Player with his name
	 * 
	 * @param @Player
	 * @returns boolean
	 */
	public boolean registerPlayer(Player p);

}
