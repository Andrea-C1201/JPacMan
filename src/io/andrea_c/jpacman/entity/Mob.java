package io.andrea_c.jpacman.entity;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.Sprite;
import io.andrea_c.jpacman.level.LevelManager;

public class Mob extends Entity {

	protected enum Direction {
		UP, DOWN, LEFT, RIGHT;
	}

	protected Direction dir;
	protected boolean moving = false;

	public Mob(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}

		if (xa > 0)
			dir = Direction.RIGHT;
		if (xa < 0)
			dir = Direction.LEFT;
		if (ya > 0)
			dir = Direction.DOWN;
		if (ya < 0)
			dir = Direction.UP;

		for (int x = 0; x < Math.abs(xa); x++) {
			if (!collision(abs(xa), ya)) {
				this.x += abs(xa);
			}
		}

		for (int y = 0; y < Math.abs(ya); y++) {
			if (!collision(xa, abs(ya))) {
				this.y += abs(ya);
			}
		}
	}

	//TODO improve precision
	private boolean collision(double xa, double ya) {
		boolean solid = false;
		int ix = x, iy = y;
		if (ya>0) {
			iy+=16;
		}
		if (xa>0) {
			ix+=16;			
		}
		if (LevelManager.level.getTile(ix, iy).isSolid())
			solid = true;
		return solid;
	}

	private int abs(double value) {
		if (value < 0)
			return -1;
		return 1;
	}

	@Override
	public void update() {
	}

	@Override
	public void render(Screen screen) {

	}

}
