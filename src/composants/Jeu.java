package composants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import composantsmap.Map;
import composantsmap.Plateforme;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Jeu extends JPanel implements Runnable {
	private int WIDTH_JEU, HEIGHT_JEU;
	private final int TEMPS_DU_SLEEP = 10;
	private boolean enCoursDAnimation = false;
	private Map mapTest;

	private ArrayList<String> keysPressed = new ArrayList<String>();

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
	 * M�thode pour dessiner la composant d'animation
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		// dessiner la map
		mapTest.dessiner(g2d);
		

	}// fin paintComponent

	@Override
	public void run() {
		while (enCoursDAnimation) {
			
			repaint();

			try {
				Thread.sleep(TEMPS_DU_SLEEP);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void demarrer() {
		if (!enCoursDAnimation) {
			Thread proc = new Thread(this);
			proc.start();
			enCoursDAnimation = true;

		}
	}

	public void creerMapTest() {

		ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
		listePlateforme.add(new Plateforme(0, 0, 500, 500));
		mapTest = new Map(listePlateforme, WIDTH_JEU, HEIGHT_JEU);

		// ajouter plateforme dans map

	}

	public String getKeyString(int keyCode) {
		String key = "";
		if (keyCode == KeyEvent.VK_W) {
			key = "w";
		} else if (keyCode == KeyEvent.VK_S) {
			key = "s";
		} else if (keyCode == KeyEvent.VK_A) {
			key = "a";
		} else if (keyCode == KeyEvent.VK_D) {
			key = "d";
		}

		return key;
	}
}
