package io.andrea_c.jpacman.level.tile;

public class CollisionBox {

	private int x;
	private int y;
	private int width;
	private int height;
	
	public CollisionBox(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean collide(CollisionBox b) {
		if(x > b.x && x < b.x+b.width)
			if(y > b.y && y < b.y+b.height)
				return true;
		return false;
	}
	
	public boolean collideWithTile(Tile tile) {
		if(tile.isSolid())
			return true;
		return false;
	}
	
}
