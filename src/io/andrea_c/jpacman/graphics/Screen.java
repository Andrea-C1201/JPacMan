package io.andrea_c.jpacman.graphics;

import io.andrea_c.jpacman.graphics.assets.Sprite;
import io.andrea_c.jpacman.graphics.assets.SpriteSheet;

public class Screen {

	private int width, height;
	public int[] pixels;

	private static final int ALPHA_COLOR = 0xff000000; // change to something like 0xffff00ff (also into the
														// spritesheet.png file

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSheet(SpriteSheet sheet, int xp, int yp) {
		for (int y = 0; y < sheet.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.getWidth()];
			}
		}
	}

	public void renderSprite(Sprite sprite, int xp, int yp) {
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				int col = sprite.getSprite()[x + y * sprite.getWidth()];
				if (col != ALPHA_COLOR)
					pixels[xa + ya * width] = col;
			}
		}
	}

}
