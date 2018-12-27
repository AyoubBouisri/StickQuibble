package composantsmap;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import geometrie.Vecteur;


public class Map {
	private static int WIDTH_MAP = 4000,HEIGHT_MAP = 2500; 
	

	public Vecteur posCamera = new Vecteur(0,0);
	public  int widthCam, heightCam;
	public ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
	
	public Map(ArrayList<Plateforme> listePlateforme, int widthCam, int heightCam) {
		this.listePlateforme = listePlateforme;
		this.widthCam = widthCam;
		this.heightCam = heightCam;

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
		
		Rectangle2D.Double plateformeRect = new Rectangle2D.Double(plateforme.position.getX(), plateforme.position.getY(), plateforme.width, plateforme.height);
		Rectangle2D.Double cameraRect = new Rectangle2D.Double(posCamera.getX(), posCamera.getY(), widthCam, heightCam);
		return cameraRect.intersects(plateformeRect);
	}
	
}
