package io.andrea_c.jpacman.level.tile;

import io.andrea_c.tiledloader.Texture;

public class Tile {
	
	private Texture texture;
	private boolean solid;
	
	public Tile(Texture texture, boolean solid) {
		this.texture = texture;
		this.solid = solid;
	}
	
	public Tile(Texture texture) {
		this(texture, false);
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public Texture getTexture() {
		return texture;
	}

}
