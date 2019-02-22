package io.andrea_c.jpacman.graphics.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private int width, height;
	private String path;
	public int[] pixels;

	public int SPRITE_WIDTH = 16; // init in constructor and make final
	public int SPRITE_HEIGHT;

	public static SpriteSheet datasheet = new SpriteSheet("/spritesheet.png");
	public static SpriteSheet pacman_up = new SpriteSheet(datasheet, 0, 2, 2, 1, 16);
	public static SpriteSheet pacman_down = new SpriteSheet(datasheet, 0, 3, 2, 1, 16);
	public static SpriteSheet pacman_left = new SpriteSheet(datasheet, 0, 1, 2, 1, 16);
	public static SpriteSheet pacman_right = new SpriteSheet(datasheet, 0, 0, 2, 1, 16);
	public static SpriteSheet background = new SpriteSheet("/background.png");

	public SpriteSheet(String path) {
		this.path = path;
		loadImage();
	}

	private Sprite[] sprites;

	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		// if (width == height)
		// SIZE = width;
		// else
		// SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize)
								+ (y0 + ya * spriteSize) * SPRITE_WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}

	}

	private void loadImage() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			this.width = SPRITE_WIDTH = image.getWidth();
			this.height = image.getHeight();
			this.pixels = new int[width * height];
			this.pixels = image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			System.err.println("Could't load " + path);
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Sprite[] getSprites() {
		return sprites;
	}

}
