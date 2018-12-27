package composants;

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
	private int WIDTH_JEU,HEIGHT_JEU;
	private Map mapTest;
	
	public Jeu(int width,int height) {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
			}
		});
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
		ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
		listePlateforme.add(new Plateforme(0, 0, 500, 500));
		mapTest = new Map(listePlateforme);
		
		// ajouter plateforme dans map
		
	}

}
