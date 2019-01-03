package composantsmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import characters.Character;
import geometrie.Collision;
import geometrie.Vecteur;

/**
 * 
 * Class that contains the informations of a map
 *
 */

public class Map {
	public static int WIDTH_MAP = 4000, HEIGHT_MAP = 2500;
	private static int NB_CHARACTERS = 2;
	private Camera cam;
	public ArrayList<Plateforme> listePlateforme = new ArrayList<Plateforme>();
	public Character[] characters = new Character[NB_CHARACTERS];

	/**
	 * Constructor of a Map
	 * 
	 * * @param widthCam Width of the camera zone
	 * 
	 * @param heightCam
	 *            Heigth of the camera zone
	 */
	public Map(int widthCam, int heightCam) {

		Vecteur posCamera = new Vecteur(WIDTH_MAP / 2 - widthCam / 2, HEIGHT_MAP / 2 - heightCam / 2);
		cam = new Camera(posCamera, widthCam, heightCam, WIDTH_MAP, HEIGHT_MAP);
		// spawn the players
		double distPlayers = 500;
		characters[0] = new Character(new Vecteur(WIDTH_MAP / 2 - distPlayers /2 , posCamera.getY() + 30),
				new Color(252, 187, 35));
		characters[1] = new Character(new Vecteur(WIDTH_MAP / 2 + distPlayers / 2, posCamera.getY() + 30),
				new Color(255, 174, 201));

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
		for (Character c : characters) {
			if (isInMap(c)) {
				c.dessinerDansEcran(g2d, cam);

			} else {

				c.dessinerHorsEcran(g2d, cam);
			}
		}

	}

	public void updateMap() {
		updateCharacters();
		cam.update(characters);
	}

	/**
	 * Method that updates the position of the characters according to their speed
	 * and moves the characters according to the inputs.
	 */
	public void updateCharacters() {
		for (Character c : characters) {
			c.update();
		}

		// check collision with floors
		for (Plateforme pl : listePlateforme) {
			for (Character c : characters) {
				if (Collision.inCollisionFloor(c, pl.floor) != null) {
					c.curJumpCount = 0;
					c.speed.setY(0);
					c.isOnGround = true;
				} else {
					c.isOnGround = false;
				}
			}

		}

	}

	
	public void moveCharacter(ArrayList<String> keysPressed) {
		Character player1 = characters[0];
		Character player2 = characters[1];
		// -------------- CONTROLS PLAYER 1 --------------------
		if (keysPressed.contains("w")) {
			keysPressed.remove("w");
			player1.jump();

		}
		if (keysPressed.contains("s")) {

		}
		if (keysPressed.contains("a") && keysPressed.contains("d")) {
			if (keysPressed.indexOf("a") < keysPressed.indexOf("d")) {
				player1.moveX(false);
			} else {
				player1.moveX(true);
			}
		} else {
			if (keysPressed.contains("a")) {
				player1.moveX(true);

			} else if (keysPressed.contains("d")) {
				player1.moveX(false);
			} else {
				player1.speed.setX(0);
			}
		}
		if(keysPressed.contains("h")) {
			player1.beginDodge();
			
		}
		
		// ----------------- CONTROLS PLAYER 2 ----------------------
		if (keysPressed.contains("up_arrow")) {
			keysPressed.remove("up_arrow");
			player2.jump();

		}
		if (keysPressed.contains("down_arrow")) {

		}
		if (keysPressed.contains("left_arrow") && keysPressed.contains("right_arrow")) {
			if (keysPressed.indexOf("left_arrow") < keysPressed.indexOf("right_arrow")) {
				player2.moveX(false);
			} else {
				player2.moveX(true);
			}
		} else {
			if (keysPressed.contains("left_arrow")) {
				player2.moveX(true);

			} else if (keysPressed.contains("right_arrow")) {
				player2.moveX(false);
			} else {
				player2.speed.setX(0);
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

	public void addPlateforme(double x, double y, double width, double height) {
		Plateforme newPlateforme = new Plateforme(x, y, width, height);
		listePlateforme.add(newPlateforme);
	}
}
