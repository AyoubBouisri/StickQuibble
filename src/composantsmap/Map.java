package composantsmap;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import geometrie.Vecteur;
/**
 * 
 * Class that contains the informations of a map 
 *
 */

public class Map {
	public static int WIDTH_MAP = 4000,HEIGHT_MAP = 2500; 
	

	public Vecteur posCamera;
	public  int widthCam, heightCam;
	public ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
	
	private double vitesseCamera = 3;
	
	/**
	 * Constructor of a Map
	 * @param listePlateforme Arraylist of platforms in the map
	 * @param widthCam Width of the camera zone 
	 * @param heightCam Heigth of the camera zone
	 */
	public Map(ArrayList<Plateforme> listePlateforme, int widthCam, int heightCam) {
		this.listePlateforme = listePlateforme;
		this.widthCam = widthCam;
		this.heightCam = heightCam;
		posCamera = new Vecteur(WIDTH_MAP/2 - widthCam/2, HEIGHT_MAP/2 - heightCam/2);

	}
	
	/**
	 * Method to draw the map
	 * @param g2d object that draw the map
	 */
	public void dessiner(Graphics2D g2d) {
		
		for(int i = 0 ;i < listePlateforme.size(); i++) {
		
			if(isInMap(listePlateforme.get(i))) {
				listePlateforme.get(i).dessinerDansEcran(g2d, posCamera);
			}
		}
		
	}
	
	public void scaleCamera() {
		
	}
	/**
	 * Method to move the camera zone in the map with "WASD" keys.
	 * @param keysPressed The array of that contains the key pressed 
	 */
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
	/**
	 * Method that verify if a platform is the camera zone 
	 * @param plateforme the platform that we use to verify
	 * @return true if the platform is in the camera zone and false if not.
	 */
	public boolean isInMap(Plateforme plateforme) {
		
		Rectangle2D.Double plateformeRect = new Rectangle2D.Double(plateforme.position.getX(), plateforme.position.getY(), plateforme.width, plateforme.height);
		Rectangle2D.Double cameraRect = new Rectangle2D.Double(posCamera.getX(), posCamera.getY(), widthCam, heightCam);
		return cameraRect.intersects(plateformeRect);
	}
	
}
