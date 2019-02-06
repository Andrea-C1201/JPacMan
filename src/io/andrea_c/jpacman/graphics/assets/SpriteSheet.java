package io.andrea_c.jpacman.graphics.assets;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private int width, height;
	private String path;
	public int[] pixels;
	
	public int SPRITE_WIDTH = 16; //init in constructor and make final
	
	public static SpriteSheet datasheet = new SpriteSheet("/spritesheet.png");
	public static SpriteSheet background = new SpriteSheet("/background.png");
	
	public SpriteSheet(String path) {
		this.path = path;
		loadImage();
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

}
