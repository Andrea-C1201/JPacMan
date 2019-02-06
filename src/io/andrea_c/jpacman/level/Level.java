package io.andrea_c.jpacman.level;

import java.util.ArrayList;
import java.util.List;

import io.andrea_c.jpacman.entity.Entity;
import io.andrea_c.jpacman.entity.Mob;
import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.assets.SpriteSheet;
import io.andrea_c.jpacman.graphics.layer.Layer;

public class Level extends Layer {

	private List<Entity> items = new ArrayList<Entity>();
	private List<Mob> ghosts = new ArrayList<Mob>();

	public Level(int levelId) {

		initLevel();
	}

	private void initLevel() {

	}

	private void renderBackground(Screen screen) {
		screen.renderSheet(SpriteSheet.background, 0, 0);
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
	}

	@Override
	public void update() {
		for (int i = 0; i < items.size(); i++) {
			items.get(i).update();
		}

		for (int i = 0; i < ghosts.size(); i++) {
			ghosts.get(i).update();
		}
	}

}
