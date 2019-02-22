package io.andrea_c.jpacman.level.tile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.andrea_c.tiledloader.TextureImageLoader;
import io.andrea_c.tiledloader.TextureLoader;

public class Tiles {

	public static Tile BLACK;
	public static Tile WHITE;
	public static Tile BLUE;
	
	private static TextureImageLoader imageLoader;

	public static void init() {
		TextureLoader loader = new TextureLoader("res/textures.json");
		imageLoader = new TextureImageLoader(loader);
		try {
			imageLoader.loadTextures(new FileInputStream(new File("res/"+loader.getFilename())));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BLUE = new Tile(imageLoader.getTextureById(2));
		BLACK = new Tile(imageLoader.getTextureById(0), false);
		WHITE = new Tile(imageLoader.getTextureById(1), false);
	}
	
	public static Tile createTile(int id, boolean solid) {
		return new Tile(imageLoader.getTextureById(id), solid);
	}

	public static Tile getTileByTextureId(int id) {
		switch (id) {
		case 0:
			return BLUE;
		case 1:
			return BLACK;
		case 2:
			return WHITE;
		}
		return null;
	}

}
