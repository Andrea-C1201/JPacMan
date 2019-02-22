package io.andrea_c.jpacman.entity;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.Sprite;
import io.andrea_c.jpacman.level.tile.CollisionBox;

public class Entity {

	protected Sprite sprite;
	protected int x, y;
	
	protected CollisionBox box;

	public Entity(Sprite sprite, int x, int y) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.box = new CollisionBox(x, y, sprite.getWidth(), sprite.getHeight());
	}

	public void update() {
	}

	public void render(Screen screen) {
		screen.renderSprite(sprite, x, y);
	}

}
