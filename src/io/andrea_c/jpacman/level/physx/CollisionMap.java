package io.andrea_c.jpacman.level.physx;

import io.andrea_c.tiledloader.level.layer.ObjectLayer;
import io.andrea_c.tiledloader.types.Object;

public class CollisionMap {

	private boolean[] solid;

	private int width, height;

	public CollisionMap(ObjectLayer layer, int width, int height) {
		this.width = width;
		this.height = height;
		dispatch(layer.getData());
	}

	private void dispatch(Object[] objs) {
		solid = new boolean[width * height];
		for (Object object : objs) {
			solid[(int) (object.getX() + object.getY() * object.getWidth())] = true;
		}
	}

	public boolean[] getData() {
		return this.solid;
	}

}
