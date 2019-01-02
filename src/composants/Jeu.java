package composants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import characters.Character;
import composantsmap.Map;
import composantsmap.Plateforme;
import geometrie.Vecteur;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Jeu extends JPanel implements Runnable {

	private int WIDTH_JEU, HEIGHT_JEU;
	private final int TEMPS_DU_SLEEP = 10;

	private boolean animating = false;
	private Map mapTest;
	// Array that keeps track of they keys currently pressed
	private ArrayList<String> keysPressed = new ArrayList<String>();

	/**
	 * Panel that keeps track of the game, prints and updates.
	 * 
	 * @param width
	 *            The width of the panel
	 * @param height
	 *            The height of the panel
	 */
	public Jeu(int width, int height) {
		this.setBounds(0, 0, width, height);
		this.setFocusable(true);
		this.setLayout(null);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				int keyCode = e.getKeyCode();
				String key = getKeyString(keyCode);

				// if the key is already in the arrayList don't add it again
				if (key != "") {
					if (!keysPressed.contains(key)) {
						keysPressed.add(key);

					}
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				int keyCode = e.getKeyCode();
				String key = getKeyString(keyCode);

				if (key != "") {
					keysPressed.remove(key);
				}

			}
		});

		WIDTH_JEU = width;
		HEIGHT_JEU = height;

		// creer map test
		creerMapTest();

		this.demarrer();

	}

	/**
	 * Method that draw the game
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// dessiner la map
		setBackground(new Color(74, 207, 251));
		mapTest.dessiner(g2d);

	}// fin paintComponent

	/**
	 * Method that redraws and updates the game depending of the sleep time.
	 */
	@Override
	public void run() {
		while (animating) {
			// move the camera
		
			mapTest.moveCharacter(keysPressed);
			mapTest.updateMap();
			repaint();

			try {
				Thread.sleep(TEMPS_DU_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Method that stars the thread to run the game.
	 */
	public void demarrer() {
		if (!animating) {
			Thread proc = new Thread(this);
			proc.start();
			animating = true;

		}
	}

	/**
	 * Method that creates a map to test the code.
	 */
	public void creerMapTest() {

		mapTest = new Map( WIDTH_JEU, HEIGHT_JEU);
		// create a plateform in the middle of the map
		double widthPlateforme = 1000;
		double heightPlateforme = widthPlateforme/ 2.5;
		double xPlateforme = Map.WIDTH_MAP / 2 - widthPlateforme / 2;
		double yPlateforme = Map.HEIGHT_MAP / 2 - heightPlateforme / 2;
		mapTest.addPlateforme(xPlateforme, yPlateforme, widthPlateforme, heightPlateforme);

	}

	/**
	 * Method that gives back the string equivalent to the inputed keyCode.
	 * 
	 * @param keyCode
	 *            Integer that represent a keyCode
	 * @return The string equivalent to the keyCode if the key is one of the
	 *         playable keys.
	 */
	public String getKeyString(int keyCode) {
		String key = "";

		switch (keyCode) {
		case KeyEvent.VK_DOWN:
			key = "down_arrow";
			break;
		case KeyEvent.VK_UP :
			key = "up_arrow";
			break;
		case KeyEvent.VK_LEFT :
			key = "left_arrow";
			break;
		case KeyEvent.VK_RIGHT :
			key = "right_arrow";
			break;
		case KeyEvent.VK_W :
			key = "w";
			break;
		case KeyEvent.VK_S :
			key = "s";
			break;
		case KeyEvent.VK_A :
			key = "a";
			break;
		case KeyEvent.VK_D :
			key = "d";
			break;	
		}
		

		return key;
	}
}
