package composantsmap;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import geometrie.Vecteur;


public class Map {
	public static int WIDTH_MAP = 4000,HEIGHT_MAP = 2500; 
	

	public Vecteur posCamera;
	public  int widthCam, heightCam;
	public ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
	
	private double vitesseCamera = 3;
	public Map(ArrayList<Plateforme> listePlateforme, int widthCam, int heightCam) {
		this.listePlateforme = listePlateforme;
		this.widthCam = widthCam;
		this.heightCam = heightCam;
		posCamera = new Vecteur(WIDTH_MAP/2 - widthCam/2, HEIGHT_MAP/2 - heightCam/2);

	}
	public void dessiner(Graphics2D g2d) {
		
		for(int i = 0 ;i < listePlateforme.size(); i++) {
		
			if(isInMap(listePlateforme.get(i))) {
				listePlateforme.get(i).dessinerDansEcran(g2d, posCamera);
			}
		}
		
	}
	
	public void scaleCamera() {
		
	}
	
	public void moveCamera(ArrayList<String> keysPressed) {
		int moveX = 0;
		int moveY = 0;
		// trouver direction de la camera
		if(keysPressed.contains("w")) {
			moveY--;
		}
		if(keysPressed.contains("s")) {
			moveY++;
		}
		if(keysPressed.contains("a")) {
			moveX--;
		}
		if(keysPressed.contains("d")) {
			moveX++;
		}
		// nouvelle position de la camera 
		
		double nouveauX = posCamera.getX() + moveX * vitesseCamera;
		double nouveauY = posCamera.getY() + moveY * vitesseCamera;
		
		// check si la camera depasse pas la map
	
		if(nouveauX < 0 ) {
			nouveauX = 0;
		}else {
			if(nouveauX + widthCam > WIDTH_MAP) {
				nouveauX = WIDTH_MAP - widthCam;
			}
		}
		
		
		if(nouveauY < 0 ) {
			nouveauX = 0;
		}else {
			if(nouveauY + heightCam > HEIGHT_MAP) {
				nouveauY = HEIGHT_MAP - heightCam;
			}
		}
		
		posCamera = new Vecteur (nouveauX,nouveauY);
		
	}
	
	public boolean isInMap(Plateforme plateforme) {
		
		Rectangle2D.Double plateformeRect = new Rectangle2D.Double(plateforme.position.getX(), plateforme.position.getY(), plateforme.width, plateforme.height);
		Rectangle2D.Double cameraRect = new Rectangle2D.Double(posCamera.getX(), posCamera.getY(), widthCam, heightCam);
		return cameraRect.intersects(plateformeRect);
	}
	
}
