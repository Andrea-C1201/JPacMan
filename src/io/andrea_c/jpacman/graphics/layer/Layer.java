package io.andrea_c.jpacman.graphics.layer;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.level.physx.CollisionMap;
import io.andrea_c.tiledloader.LayerLoader;
import io.andrea_c.tiledloader.exceptions.LayerNotFoundException;
import io.andrea_c.tiledloader.level.layer.LayerType;
import io.andrea_c.tiledloader.level.layer.ObjectLayer;
import io.andrea_c.tiledloader.level.layer.TileLayer;

public class Layer {

	private String layerName;
	
	private TileLayer tileLayer; //TODO maybe move to Level.java(?)
	private ObjectLayer objectLayer;
	private CollisionMap collisionMap;
	
	private int width, height;
	
	public Layer(String layerName) {
		this.layerName = layerName;
		createLayer();
		collisionMap = new CollisionMap(objectLayer, tileLayer.getWidth(), tileLayer.getHeight());
	}

	private void createLayer() {
		LayerLoader layerLoader = new LayerLoader("res/level.json");
		try {
			tileLayer = (TileLayer) layerLoader.loadLayer(layerName, LayerType.TILE_LAYER);
			this.width = tileLayer.getWidth();
			this.height = tileLayer.getHeight();
			objectLayer = (ObjectLayer) layerLoader.loadLayer("Collision", LayerType.OBJECT_GROUP);
		} catch (LayerNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public int[] getTextureData() {
		return tileLayer.getData();
	}
	
	public boolean[] getCollisionData() {
		return collisionMap.getData();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public void render(Screen screen) {
	}

	public void update() {
	}

}
