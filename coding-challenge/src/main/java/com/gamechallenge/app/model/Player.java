package com.gamechallenge.app.model;

import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * This class contains the player model which is the 
 * schema to register a player
 * 
 */

@Data
@Component
public class Player {

	private String name;
}
