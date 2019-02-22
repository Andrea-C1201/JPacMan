package io.andrea_c.jpacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import io.andrea_c.jpacman.graphics.Screen;
import io.andrea_c.jpacman.graphics.layer.Layer;
import io.andrea_c.jpacman.input.Input;
import io.andrea_c.jpacman.level.Level;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static int scale = 3;
	private static int width = 224 * scale;// 912(scale = 4);
	private static int height = 248*scale;// 992(scale = 4);
	public static String title = "JPacMan";

	private Thread thread;
	private JFrame frame;
	private boolean running = false;

	private Screen screen;
	private BufferedImage image;
	private int[] pixels;

	private Input input;

	private List<Layer> layerStack = new ArrayList<Layer>();

	public Main() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);

		image = new BufferedImage(width / 3, height / 3, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		input = new Input();
		addKeyListener(input);

		screen = new Screen(width / 3, height / 3);
		frame = new JFrame();

		Level l = new Level(0);
		l.setInputManager(input);
		l.initLevel();
		layerStack.add(l);

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, title);
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  |  " + updates + " ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void update() {
		input.update();
		for (int i = 0; i < layerStack.size(); i++) {
			layerStack.get(i).update();
		}
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		for (int i = 0; i < layerStack.size(); i++) {
			layerStack.get(i).render(screen);
		}

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(0xff00ff));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Main game = new Main();
		game.frame.setResizable(false);
		game.frame.setTitle(Main.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		game.start();
	}

}
