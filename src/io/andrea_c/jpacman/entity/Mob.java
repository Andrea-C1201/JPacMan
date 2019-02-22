package io.andrea_c.jpacman.entity;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.Sprite;

public class Mob extends Entity {

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT, STOP;
	}

	protected Direction dir;
	protected boolean moving = false;

	public Mob(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public void move() {
		if (moving)
			if (dir == Direction.UP) {
				// moving = true;
				y--;
			} else if (dir == Direction.DOWN) {
				// moving = true;
				y++;
			} else if (dir == Direction.RIGHT) {
				// moving = true;
				x++;
			} else if (dir == Direction.LEFT) {
				// moving = true;
				x--;
			} else {
				// moving = false;
			}
	}

	@Override
	public void update() {
		move();
	}

	@Override
	public void render(Screen screen) {

	}

}
