package io.andrea_c.jpacman.level.physx;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
			int x = (int) object.getX();
			int y = (int) object.getY();
			int width = (int) object.getWidth();
			int height = (int) object.getHeight();
			if (width == height) {
				solid[x + y * this.width] = true;
			} else if (width > height) {
				for (int i = 0; i < width; i++) {
					solid[(x + i) + y * this.width] = true;
				}
			} else {
				for (int i = 0; i < height; i++) {
					solid[x + (i + y) * this.width] = true;
				}
			}

		}
		//DEBUG STUFF
		try {
			BufferedWriter fw = new BufferedWriter(new FileWriter(new File("idk.txt")));
			for (int i = 0; i < solid.length; i++) {
				if (i % this.width == 0)
					fw.append("\n");
				if (!solid[i])
					fw.append("0 ");
				else
					fw.append("1 ");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean[] getData() {
		return this.solid;
	}

}
