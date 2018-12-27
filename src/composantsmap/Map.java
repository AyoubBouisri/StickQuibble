package composantsmap;

import java.awt.Graphics2D;
import java.util.ArrayList;


public class Map {
	private static int WIDTH_MAP = 4000,HEIGHT_MAP = 2500; 
	
	private int xCamera,yCamera;
	public ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
	public Map() {
		// TEST SEULEMENT A CHANGER
		
	}
	public void dessiner(Graphics2D g2d) {
		
		for(int i = 0 ;i < listePlateforme.size(); i++) {
			listePlateforme.get(i).dessiner(g2d);
		}
		
	}
	
	public void scaleCamera() {
		
	}
	
	public void moveCamera() {
		
	}
	
}
