package io.andrea_c.jpacman.graphics.assets;

public class Sprite {

	private SpriteSheet sheet;
	private int x, y, width, height;
	private int[] pixels;

	public static Sprite sprite = new Sprite(SpriteSheet.datasheet, 0, 0, 16);

	public Sprite(SpriteSheet sheet, int x, int y, int size) {
		this.sheet = sheet;
		this.x = x * size;
		this.y = y * size;
		this.width = size;
		this.height = size;
		this.pixels = new int[width * height];
		load();
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

	public int[] getSprite() {
		return this.pixels;
	}
	
	public Sprite getScaledSprite(Sprite sprite, int scaleFactor) {
		return null;
	}
	
	private void scale(int scaleFactor, int[] pixels){
		
	}

}
