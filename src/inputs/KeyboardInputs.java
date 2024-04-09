package inputs;

import static utilz.Constants.Directions.DOWN;
import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.Directions.ROTATE;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInputs implements KeyListener {
	private GamePanel gamePanel;
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gamePanel.setDirection(ROTATE);
			break;
		case KeyEvent.VK_A:
			gamePanel.setDirection(LEFT);
			break;
		case KeyEvent.VK_S:
			gamePanel.setDirection(DOWN);
			gamePanel.downPressed();
			break;
		case KeyEvent.VK_D:
			gamePanel.setDirection(RIGHT);
			break;
		}
		
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			gamePanel.setMoving(false);
			break;
		case KeyEvent.VK_A:
			gamePanel.setMoving(false);
			break;
		case KeyEvent.VK_S:
			gamePanel.setMoving(false);
			break;
		case KeyEvent.VK_D:
			gamePanel.setMoving(false);
			break;
		}
		
	}

}
