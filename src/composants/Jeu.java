package composants;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import composantsmap.Map;

public class Jeu extends JPanel implements Runnable {
	private int WIDTH_JEU,HEIGHT_JEU;
	private Map mapTest;
	
	public Jeu(int width,int height) {
		WIDTH_JEU = width;
		HEIGHT_JEU = height;
		
		// creer map test 
		creerMapTest();
		
	}
	
	/**
	 * Méthode pour dessiner la composant d'animation
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
		
		
	}
	
	public void creerMapTest() {
		mapTest = new Map();
		
		// ajouter plateforme dans map
		
	}

}
