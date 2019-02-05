package io.andrea_c.jpacman;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import io.andrea_c.jpacman.graphics.Screen;

public class Main extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800, HEIGHT = 600;
	private static final String TITLE = "JPacMan";

	private int[] pixels;
	private BufferedImage image;

	private JFrame frame;
	private Screen screen;

	private Thread thread;
	private boolean running = false;

	public Main() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame = new JFrame();
		screen = new Screen(WIDTH, HEIGHT);
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	@Override
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
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();

		for (int i = 0; i < pixels.length; i++)
			pixels[i] = screen.pixels[i];
		Graphics g = getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g.dispose();
		bs.show();
	}

	public void update() {

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, TITLE);
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

	public static void main(String[] args) {
		Main m = new Main();

		m.frame.setResizable(false);
		m.frame.setTitle(TITLE);
		m.frame.add(m);
		m.frame.pack();
		m.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.frame.setLocationRelativeTo(null);
		m.frame.setVisible(true);

		m.start();
	}

}
