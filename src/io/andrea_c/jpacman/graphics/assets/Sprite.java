package io.andrea_c.jpacman.graphics.assets;

public class Sprite {

	protected SpriteSheet sheet;
	private int x, y, width, height;
	private int[] pixels;

	public static Sprite pacman_right = new Sprite(SpriteSheet.datasheet, 1, 0, 16);
	public static Sprite pacman_left = new Sprite(SpriteSheet.datasheet, 1, 1, 16);
	public static Sprite pacman_up = new Sprite(SpriteSheet.datasheet, 1, 2, 16);
	public static Sprite pacman_down = new Sprite(SpriteSheet.datasheet, 1, 3, 16);

	public Sprite(SpriteSheet sheet, int x, int y, int size) {
		this.sheet = sheet;
		this.x = x * size;
		this.y = y * size;
		this.width = size;
		this.height = size;
		this.pixels = new int[width * height];
		load();
	}

	protected Sprite(SpriteSheet sheet, int width, int height) {
		//SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int[] pixels, int width, int height) {
		//SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}

	}

	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SPRITE_WIDTH];
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int[] getSpritePixels() {
		return this.pixels;
	}

}
