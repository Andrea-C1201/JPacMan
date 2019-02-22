package io.andrea_c.jpacman.entity;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.AnimatedSprite;
import io.andrea_c.jpacman.graphics.assets.Sprite;
import io.andrea_c.jpacman.graphics.assets.SpriteSheet;
import io.andrea_c.jpacman.input.Input;
import io.andrea_c.jpacman.level.Level;

public class Pacman extends Mob {

	private Input input;
	private Level level;

	private AnimatedSprite pacman_up = new AnimatedSprite(SpriteSheet.pacman_up, 16, 16, 2, 20);
	private AnimatedSprite pacman_down = new AnimatedSprite(SpriteSheet.pacman_down, 16, 16, 2, 20);
	private AnimatedSprite pacman_left = new AnimatedSprite(SpriteSheet.pacman_left, 16, 16, 2, 20);
	private AnimatedSprite pacman_right = new AnimatedSprite(SpriteSheet.pacman_right, 16, 16, 2, 20);
	private AnimatedSprite animSprite = pacman_up;

	public Pacman(Input input, Level level, int x, int y) {
		super(Sprite.pacman_up, x, y);
		this.input = input;
		this.level = level;
	}

	@Override
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderSprite(sprite, x, y);
	}

	@Override
	public void update() {
		if (moving)
			animSprite.update();
		else
			animSprite.setFrame(0);
		super.update();
	}

	@Override
	public void move() {
		if (input.up) {
			dir = Direction.UP;
			animSprite = pacman_up;
		} else if (input.down) {
			dir = Direction.DOWN;
			animSprite = pacman_down;
		} else if (input.left) {
			dir = Direction.LEFT;
			animSprite = pacman_left;
		} else if (input.right) {
			dir = Direction.RIGHT;
			animSprite = pacman_right;
		} else {
			dir = Direction.STOP;
		}
		collide();
		super.move();
	}

	private void collide() {
		/*if (dir == Direction.UP) {
			if (box.collideWithTile(level.getTile(x, y - 1))) {
				moving = false;
			} else
				moving = true;
		} else if (dir == Direction.DOWN) {
			if (box.collideWithTile(level.getTile(x, y + 1))) {
				moving = false;
			} else
				moving = true;
		} else*/ if (dir == Direction.LEFT) {
			if (box.collideWithTile(level.getTile(x - 1, y))) {
				moving = false;
			} else
				moving = true;
		} /*else if (dir == Direction.RIGHT) {
			if (box.collideWithTile(level.getTile(x + 1, y))) {
				moving = false;
			} else
				moving = true;
		}*/
	}

}
