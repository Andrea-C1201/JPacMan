package io.andrea_c.jpacman.level;

import java.util.ArrayList;
import java.util.List;

import io.andrea_c.jpacman.entity.Entity;
import io.andrea_c.jpacman.entity.Mob;
import io.andrea_c.jpacman.entity.Pacman;
import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.layer.Layer;
import io.andrea_c.jpacman.input.Input;
import io.andrea_c.jpacman.level.tile.Tile;
import io.andrea_c.jpacman.level.tile.Tiles;

public class Level extends Layer {

	private Tile[] tiles;

	private Pacman player;
	private List<Entity> items = new ArrayList<Entity>();
	private List<Mob> ghosts = new ArrayList<Mob>();

	private Input input;

	public Level(int levelId) {
		super("Texture");
		Tiles.init();
	}

	public void initLevel() {
		player = new Pacman(input, 116, 22);
		tiles = new Tile[super.getTextureData().length];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = Tiles.createTile(super.getTextureData()[i], super.getCollisionData()[i]);
			// tiles[i] = Tiles.getTileByTextureId(super.getTextureData()[i]);
		}
	}

	private void renderBackground(Screen screen) {
		int x = 0;
		int y = 0;
		for (int i = 0; i < tiles.length; i++) {
			if (tiles[i] != null)
				screen.renderTile(tiles[i], x, y);
			x++;
			if (x >= screen.getWidth()) {
				y++;
				x = 0;
			}
		}
	}

	@Override
	public void render(Screen screen) {
		renderBackground(screen);

		for (int i = 0; i < items.size(); i++) {
			items.get(i).render(screen);
		}

		for (int i = 0; i < ghosts.size(); i++) {
			ghosts.get(i).render(screen);
		}
		player.render(screen);
	}

	@Override
	public void update() {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update();
		}

		for (int i = 0; i < ghosts.size(); i++) {
			ghosts.get(i).update();
		}
		player.update();
	}

	public void setInputManager(Input input) {
		this.input = input;
	}

	public Tile getTile(int x, int y) {
		int val = x + y * getWidth();
		if (val > 0 && val < tiles.length)
			return tiles[val];
		return null;
	}

}
