package io.andrea_c.jpacman.level;

import io.andrea_c.jpacman.input.Input;

public class LevelManager {
	
	public static Level level;
	
	public static void createLevel(Input input) {
		level = new Level(0);
		level.setInputManager(input);
		level.initLevel();
	}

}
