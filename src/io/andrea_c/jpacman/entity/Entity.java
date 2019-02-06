package io.andrea_c.jpacman.entity;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.Sprite;

public class Entity {

	protected Sprite sprite;
	protected int x, y;

	public Entity(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
	}

	

	public void update() {}

	public void render(Screen screen) {
		screen.renderSprite(sprite, x >> 16, y >> 16);
	}

}
