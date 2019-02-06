package io.andrea_c.jpacman.entity;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.Sprite;

public class Mob extends Entity {
	
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}
	
	private Direction dir;

	public Mob(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public void move() {
		if (dir == Direction.UP)
			x++;
		else if (dir == Direction.DOWN)
			x--;
		if (dir == Direction.RIGHT)
			y++;
		else if (dir == Direction.LEFT)
			y--;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Screen screen) {
		
	}
	
}
