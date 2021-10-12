package com.gamechallenge.app.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class GamePlay {
	
	private String playerName;
	private int posX;
	private int posY;
}
