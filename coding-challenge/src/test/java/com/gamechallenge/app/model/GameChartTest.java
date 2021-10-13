package com.gamechallenge.app.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GameChartTest {
	
	@Autowired
	private GameChart gc;
	
	private void init() {
		Player p1 = new Player();
		p1.setName("p1");
		Player p2 = new Player();
		p2.setName("p2");
		List<Player> playersList = new ArrayList<Player>();
		playersList.add(p1);
		playersList.add(p2);
		gc.init();
		gc.setPlayers(playersList);
	}
	
	@Test
	public void placePawnTest_PawnIsAlreadyInPlace() {
		init();
		int[][] arr = { { 1, 2, 0 }, 
						{ 2, 1, 0 }, 
						{ 2, 2, 0 } };
		gc.setGameChart(arr);
		String expectedGameStatus = "Error!! Pawn is already in place";
		String playerName = "p1";
		GamePlay gp = new GamePlay();
		gp.setPlayerName(playerName);
		gp.setPosX(0);
		gp.setPosY(0);
		gc.placePawn(gp);
		Assertions.assertEquals(expectedGameStatus, gc.getGameStatus());
	}
	
	@Test
	public void placePawnTest_Player1Wins_MajDiag() {
		init();
		int[][] arr = { { 1, 2, 0 }, 
						{ 2, 1, 0 }, 
						{ 2, 2, 0 } };
		gc.setGameChart(arr);
		String expectedGameStatus = "Game Finished p1 Won the game!!";
		String playerName = "p1";
		GamePlay gp = new GamePlay();
		gp.setPlayerName(playerName);
		gp.setPosX(2);
		gp.setPosY(2);
		gc.placePawn(gp);
//		System.out.println(gc.getGameStatus());
		Assertions.assertEquals(expectedGameStatus, gc.getGameStatus());
	}
	
	
	@Test
	public void placePawnTest_Player1Wins_MinDiag() {
		init();
		int[][] arr = { { 1, 2, 0 }, 
						{ 2, 1, 0 }, 
						{ 1, 2, 0 } };
		gc.setGameChart(arr);
		String expectedGameStatus = "Game Finished p1 Won the game!!";
		String playerName = "p1";
		GamePlay gp = new GamePlay();
		gp.setPlayerName(playerName);
		gp.setPosX(0);
		gp.setPosY(2);
		gc.placePawn(gp);
		Assertions.assertEquals(expectedGameStatus, gc.getGameStatus());
	}
	
	@Test
	public void placePawnTest_Player2Wins_MajDiag() {
		init();
		String togglePlayer = "p1";
		String expectedGameStatus = "Game Finished p2 Won the game!!";
		int[][] arr = { { 2, 2, 0 }, 
						{ 1, 2, 0 }, 
						{ 1, 1, 0 } };
		gc.setGameChart(arr);
		gc.setTogglePlayer(togglePlayer);
		String playerName = "p2";
		GamePlay gp = new GamePlay();
		gp.setPlayerName(playerName);
		gp.setPosX(2);
		gp.setPosY(2);
		gc.placePawn(gp);
//		System.out.println(gc.getGameStatus());
		Assertions.assertEquals(expectedGameStatus, gc.getGameStatus());
	}
	
	
	@Test
	public void placePawnTest_Player2Wins_MinDiag() {
		init();
		String togglePlayer = "p1";
		String expectedGameStatus = "Game Finished p2 Won the game!!";
		int[][] arr = { { 1, 2, 0 }, 
						{ 1, 2, 0 }, 
						{ 2, 1, 0 } };
		gc.setGameChart(arr);
		gc.setTogglePlayer(togglePlayer);
		String playerName = "p2";
		GamePlay gp = new GamePlay();
		gp.setPlayerName(playerName);
		gp.setPosX(0);
		gp.setPosY(2);
		gc.placePawn(gp);
//		System.out.println(gc.getGameStatus());
		Assertions.assertEquals(expectedGameStatus, gc.getGameStatus());
	}
	
}
