package io.andrea_c.jpacman.entity;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.AnimatedSprite;
import io.andrea_c.jpacman.graphics.assets.Sprite;
import io.andrea_c.jpacman.graphics.assets.SpriteSheet;
import io.andrea_c.jpacman.input.Input;

public class Pacman extends Mob {

	private Input input;

	private boolean walking = false;

	private AnimatedSprite pacman_up = new AnimatedSprite(SpriteSheet.pacman_up, 16, 16, 2, 20);
	private AnimatedSprite pacman_down = new AnimatedSprite(SpriteSheet.pacman_down, 16, 16, 2, 20);
	private AnimatedSprite pacman_left = new AnimatedSprite(SpriteSheet.pacman_left, 16, 16, 2, 20);
	private AnimatedSprite pacman_right = new AnimatedSprite(SpriteSheet.pacman_right, 16, 16, 2, 20);
	private AnimatedSprite animSprite = pacman_up;

	public Pacman(Input input, int x, int y) {
		super(Sprite.pacman_up, x, y);
		this.input = input;
	}

	@Override
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderSprite(sprite, x, y);
	}

	@Override
	public void update() {
		if (walking)
			animSprite.update();
		else
			animSprite.setFrame(0);

		int xa = 0, ya = 0;
		if (input.up) {
			animSprite = pacman_up;
			ya -= 1;
		} else if (input.down) {
			animSprite = pacman_down;
			ya += 1;
		} else if (input.left) {
			animSprite = pacman_left;
			xa -= 1;
		} else if (input.right) {
			animSprite = pacman_right;
			xa += 1;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

}
