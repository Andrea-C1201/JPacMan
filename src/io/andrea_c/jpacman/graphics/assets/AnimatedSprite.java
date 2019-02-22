package io.andrea_c.jpacman.graphics.assets;

public class AnimatedSprite extends Sprite {

	protected int frame = 0;
	protected Sprite sprite;
	protected int rate = 5;
	protected int time = 0;
	protected int length = -1;

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length, int framerate) {
		super(sheet, width, height);
		this.length = length;
		this.rate = framerate;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length)
			System.err.println("Error! Length of animation is too long");
	}
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		this(sheet, width, height, length, 5);
	}

	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= length - 1)
				frame = 0;
			else
				frame++;
			sprite = sheet.getSprites()[frame];
		}
		// System.out.println(sprite + ", Frame: " + frame);
	}

	public Sprite getSprite() {
		return this. sprite;
	}

	public void setFrameRate(int frames) {
		rate = frames;
	}

	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("IndexOutOfBoundsException in" + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}

	public int getActualFrame() {
		return this.frame;
	}

}
