package composantsmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import characters.Character;
import geometrie.Vecteur;

/**
 * 
 * Class that contains the informations of a map
 *
 */

public class Map {
	public static int WIDTH_MAP = 4000, HEIGHT_MAP = 2500;

	private Camera cam;
	public ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
	// public ArrayList<Character> listeCharacters = new ArrayList<Character>();
	public Character player1;

	/**
	 * Constructor of a Map
	 * 
	 * @param listePlateforme
	 *            Arraylist of platforms in the map
	 * @param widthCam
	 *            Width of the camera zone
	 * @param heightCam
	 *            Heigth of the camera zone
	 */
	public Map(ArrayList<Plateforme> listePlateforme, ArrayList<Character> listeCharacters, int widthCam,
			int heightCam) {
		this.listePlateforme = listePlateforme;
		// this.listeCharacters = listeCharacters;
		Vecteur posCamera = new Vecteur(WIDTH_MAP / 2 - widthCam / 2, HEIGHT_MAP / 2 - heightCam / 2);
		cam = new Camera(posCamera, widthCam, heightCam);
		player1 = new Character(new Vecteur(posCamera.getX() + cam.width / 2, posCamera.getY() + 30));

	}

	/**
	 * Method to draw the map
	 * 
	 * @param g2d
	 *            object that draw the map
	 */
	public void dessiner(Graphics2D g2d) {

		for (int i = 0; i < listePlateforme.size(); i++) {

			if (isInMap(listePlateforme.get(i))) {
				listePlateforme.get(i).dessinerDansEcran(g2d, cam);
			}

		}
		

		if (isInMap(player1)) {
			player1.dessinerDansEcran(g2d, cam);

		} else {

			player1.dessinerHorsEcran(g2d, cam);
		}

	}

	public void updateMap() {
		// update the characters
		updateCharacters();
	}

	public void scaleCamera() {

	}

	/**
	 * Method that updates the position of the characters according to their speed
	 * and moves the characters according to the inputs.
	 */
	public void updateCharacters() {
		player1.update();
	}

	/**
	 * Method to move the camera zone in the map with "WASD" keys.
	 * 
	 * @param keysPressed
	 *            The array of that contains the key pressed
	 */
	public void moveCamera(ArrayList<String> keysPressed) {
		int moveX = 0;
		int moveY = 0;
		// trouver direction de la camera
		if (keysPressed.contains("up_arrow")) {
			moveY--;
		}
		if (keysPressed.contains("down_arrow")) {
			moveY++;
		}
		if (keysPressed.contains("left_arrow")) {
			moveX--;
		}
		if (keysPressed.contains("right_arrow")) {
			moveX++;
		}
		// nouvelle position de la camera

		double nouveauX = cam.position.getX() + moveX * cam.vitesse;
		double nouveauY = cam.position.getY() + moveY * cam.vitesse;

		// check si la camera depasse pas la map

		if (nouveauX < 0) {
			nouveauX = 0;
		} else {
			if (nouveauX + cam.width > WIDTH_MAP) {
				nouveauX = WIDTH_MAP - cam.width;
			}
		}

		if (nouveauY < 0) {
			nouveauX = 0;
		} else {
			if (nouveauY + cam.height > HEIGHT_MAP) {
				nouveauY = HEIGHT_MAP - cam.height;
			}
		}

		Vecteur posCamera = new Vecteur(nouveauX, nouveauY);
		cam.position = posCamera;
	}
	
	
	public void moveCharacter(ArrayList<String> keysPressed) {
		if(keysPressed.contains("w")) {
			keysPressed.remove("w");
			player1.jump();
			
		}
		if(keysPressed.contains("s")) {
			
		}
		if(keysPressed.contains("a") &&keysPressed.contains("d")) {
			if(keysPressed.indexOf("a") < keysPressed.indexOf("d")) {
				player1.moveX(false);
			} else{
				player1.moveX(true);
			}
		} else {
			if(keysPressed.contains("a")) {
				player1.moveX(true);
				System.out.println("left");
			}else if(keysPressed.contains("d")) {
				player1.moveX(false);
			} else {
				player1.speed.setX(0);
			}
		}
		
		
		
	}

	/**
	 * Method that verify if a platform is the camera zone
	 * 
	 * @param plateforme
	 *            the platform that we use to verify
	 * @return true if the platform is in the camera zone and false if not.
	 */
	public boolean isInMap(Plateforme plateforme) {

		Rectangle2D.Double plateformeRect = new Rectangle2D.Double(plateforme.position.getX(),
				plateforme.position.getY(), plateforme.width, plateforme.height);
		Rectangle2D.Double cameraRect = new Rectangle2D.Double(cam.position.getX(), cam.position.getY(), cam.width,
				cam.height);
		return cameraRect.intersects(plateformeRect);
	}

	public boolean isInMap(Character character) {

		Rectangle2D.Double characterRect = new Rectangle2D.Double(character.position.getX(), character.position.getY(),
				character.WIDTH, character.WIDTH);
		Rectangle2D.Double cameraRect = new Rectangle2D.Double(cam.position.getX(), cam.position.getY(), cam.width,
				cam.height);
		return cameraRect.intersects(characterRect);
	}

}
