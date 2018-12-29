package characters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import composantsmap.Camera;
import composantsmap.Map;
import geometrie.Vecteur;

public class Character {
	public final static int JUMP_HEIGHT = 50;
	public final static int MAX_SPEED_DOWN = 30;

	public final static double GRAV_SPEED = 3;
	public final static int JUMP_SPEED = 30;

	public int damage;
	public final Vecteur spawnPos;
	public Vecteur position;
	public Vecteur centrePos;
	public Vecteur speed;;
	public Ellipse2D.Double hitBox;
	public final static int WIDTH = 40, HEIGHT = 60;

	public int curJumpCount = 0;

	public Character(Vecteur position) {
		spawnPos = position;
		this.position = position;
		centrePos = new Vecteur(position.getX() + WIDTH / 2, position.getY() + HEIGHT/2);
		speed = new Vecteur();
		damage = 0;
		hitBox = new Ellipse2D.Double(position.getX(), position.getY(), WIDTH, HEIGHT);
	}

	public void dessinerDansEcran(Graphics2D g2d, Camera camera) {
		double xEcran = position.getX() - camera.position.getX();
		double yEcran = position.getY() - camera.position.getY();
		hitBox.setFrame(xEcran, yEcran, WIDTH, HEIGHT);
		g2d.setColor(new Color(252,187,35));
		g2d.fill(hitBox);
	}

	public void dessinerHorsEcran(Graphics2D g2d, Camera camera) {

	}

	public void update() {
		// update the speed after grav
		double speedBeforeGrav = speed.getY();
		speed.setY(speed.getY() + GRAV_SPEED);
		position = position.additionne(speed);
		centrePos = centrePos.additionne(speed);
		speed.setY(speedBeforeGrav);
		
		if(isOutOfMap()) {
			// respawn 
			position = spawnPos.copy();
		}
	}

	/**
	 * Method that checks if the character is out of the map and returns true if the
	 * Character is out of the bounds of the map.
	 * 
	 */
	public boolean isOutOfMap() {

		return (position.getX() + WIDTH < 0 || position.getX() > Map.WIDTH_MAP || position.getY() + HEIGHT < 0
				|| position.getY() > Map.HEIGHT_MAP);

	}

	public void jump() {
		speed.setY(30);
	}

	public boolean canJump() {
		return (curJumpCount == 1 || curJumpCount == 0);
	}
	
	public void setPosition(Vecteur newPos) {
		position = newPos.copy();
		centrePos = new Vecteur(position.getX() + WIDTH / 2, position.getY() + HEIGHT/2);
		
	}

}
