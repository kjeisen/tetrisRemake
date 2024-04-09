package main;
import java.io.*;
import java.net.URL;

import javax.sound.sampled.*;

public class Game implements Runnable {
	private Thread gameThread;
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private static boolean stop = false;
	private final int FPS_SET = 20;
	private Thread musicThread;
	private Clip clip;
	public Game() {
		gamePanel = new GamePanel();
		gameWindow = new GameWindow(gamePanel);
		
		
		startGameLoop();
		startMusicLoop();
	}
	private void startMusicLoop()
	{
		musicThread = new Thread(() -> {
			try {
				URL is = getClass().getResource("/tetris_theme_song.wav");
			    AudioInputStream stream = AudioSystem.getAudioInputStream(is);
			    clip = AudioSystem.getClip();
			    clip.open(stream);
			    clip.loop(clip.LOOP_CONTINUOUSLY);
			    clip.start();
			}
			catch (Exception e) {
				musicThread.stop();
			}
			while(true) {
				if(stop) {
						clip.stop();
					}
			}
			
		});
		musicThread.start();
	}
	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	@Override
	public void run() {
		
		double timePerFrame = 1_000_000_000.0/ FPS_SET;
		long now = System.nanoTime();
		long lastFrame = System.nanoTime();
		int frames = 0;
		long lastCheck = System.currentTimeMillis();
		while(true) {
			now = System.nanoTime();
			if(now - lastFrame >= timePerFrame) {
				gamePanel.repaint();
				lastFrame = now;
				frames++;
			}
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				frames = 0;
				gamePanel.moveDown();
			}
			
			if(stop) {
			gameThread.stop();
			musicThread.stop();
			clip.stop();
			}
		}
		
	}
	public static void setStop(boolean value) {
		stop = value;
	}
	
	
	
}
