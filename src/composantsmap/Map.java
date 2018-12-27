package composantsmap;

import java.awt.Graphics2D;
import java.util.ArrayList;

import geometrie.Vecteur;


public class Map {
	private static int WIDTH_MAP = 4000,HEIGHT_MAP = 2500; 
	

	private Vecteur vecCamera = new Vecteur(0,0);
	private int widthCamera = 1300, heightCamera = 700;
	private ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
	public Map(ArrayList<Plateforme> listePlateforme) {
		this.listePlateforme = listePlateforme;

	}
	public void dessiner(Graphics2D g2d) {
		
		for(int i = 0 ;i < listePlateforme.size(); i++) {
			if(isInMap(listePlateforme.get(i))) {
				listePlateforme.get(i).dessiner(g2d);
			}
		}
		
	}
	
	public void scaleCamera() {
		
	}
	
	public void moveCamera() {
		
	}
	public boolean isInMap(Plateforme plateforme) {
		if(123 - vecCamera.getX()  > 0 &&  123 -vecCamera.getX()+ widthCamera < 0 && 321 - vecCamera.getY() < 0 && 321 - vecCamera.getY() + heightCamera >0) {
			return true;
		}
		
		
		return false;
	}
	
}
