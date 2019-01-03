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
	public final static int MAX_SPEED_DOWN = 10;
	public final static int SPEED_HORIZON = 4;
	public final static int JUMPING_POWER = -5;
	public final static int DODGING_FRAME = 20;
	public final static int DODGE_COOLDOWN_MAX = 150; // number of frame
	public final static double COEF_DODGING_SPEED = 0.8;
	public final static double THRESHOLD_YSPEED_DODGE = -3;

	public final static double GRAV = 0.15;
	public final static int JUMP_SPEED = 30;
	
	public final Color color;
	
	public final Vecteur spawnPos;
	public Vecteur position;
	public Vecteur centrePos;
	public Vecteur speed;;
	public Vecteur dodgingVec;
	public Ellipse2D.Double hitBox;
	public final static int WIDTH = 50, HEIGHT = 60;
	public final int CIRCLE_WIDTH = HEIGHT * 2; // width of the circle when the character is out of the screen
	
	public boolean isOnGround = false;
	public boolean isDodging = false; // does not include sidestep
	
	public int curDodgingFrame = 0 ;
	public int dodgeCooldown = 0;


	public int curJumpCount = 0;

	public Character(Vecteur position,Color color) {
		spawnPos = position;
		this.position = position;
		this.color = color;
		centrePos = new Vecteur(position.getX() + WIDTH / 2, position.getY() + HEIGHT/2);
		speed = new Vecteur();
		
		hitBox = new Ellipse2D.Double(position.getX(), position.getY(), WIDTH, HEIGHT);
		dodgingVec = new Vecteur();
	}

	public void dessinerDansEcran(Graphics2D g2d, Camera camera) {
		double xEcran = position.getX() - camera.position.getX();
		double yEcran = position.getY() - camera.position.getY();
		hitBox.setFrame(xEcran, yEcran, WIDTH, HEIGHT);
		g2d.setColor(color);
		if(isDodging) {
			g2d.setColor(Color.WHITE);
		}
		g2d.fill(hitBox);
	}
	/**
	 * Method that draws the character at the side of the screen in a circle if the character is out of the screen.
	 * @param g2d
	 * @param camera
	 */
	public void dessinerHorsEcran(Graphics2D g2d, Camera camera) {
		double xCircle = 0,yCircle= 0;
		final double OFF_SET = 10;
		if(position.getX() < camera.position.getX()) {
			
			xCircle = OFF_SET;
			yCircle = position.getY() - camera.position.getY() + HEIGHT / 2;
			
		}else if(position.getX() > camera.position.getX() + camera.width) {
			xCircle = camera.position.getX() +  camera.width - CIRCLE_WIDTH - camera.position.getX() - OFF_SET;
			yCircle = position.getY() - camera.position.getY() + HEIGHT / 2;
		}else if(position.getY() < camera.position.getY()) {
			xCircle = position.getX() - camera.position.getX() + WIDTH / 2;
			yCircle = OFF_SET;
		}else if(position.getY() > camera.position.getY() + camera.height) {
			
			xCircle = position.getX() - camera.position.getX() + WIDTH /2;
			yCircle = camera.position.getY() + camera.height - CIRCLE_WIDTH - camera.position.getY() - OFF_SET;
		}
		
		
		Ellipse2D.Double circle = new Ellipse2D.Double(xCircle, yCircle, CIRCLE_WIDTH, CIRCLE_WIDTH);
		g2d.setColor(color.WHITE);
		g2d.fill(circle);
		g2d.setColor(color.BLACK);
		g2d.draw(circle);
		
		// draw the character 
		drawCharacter(g2d,xCircle + CIRCLE_WIDTH/2 - WIDTH/2,yCircle + CIRCLE_WIDTH/2 - HEIGHT/2);
		
	}
	/**
	 * Methode that draws only the character. TO BE COMPLETED WITH ANIMATIONS AND ACTUAL DRAWINGS
	 * @param g2d
	 * @param x
	 * @param y
	 */
	public void drawCharacter(Graphics2D g2d,double x,double y) {
		Ellipse2D.Double c = new Ellipse2D.Double(x, y, WIDTH, HEIGHT);
		g2d.setColor(color);
		g2d.fill(c);
	}
	public void update() {
		// update the speed after grav
		if(isDodging) {
			dodge();
			setPosition(position.additionne(dodgingVec));
		} else { 
			if(dodgeCooldown > 0) {
				dodgeCooldown -=1;
			}
			double speedWithGrav = speed.getY() + GRAV;
			if(speedWithGrav > MAX_SPEED_DOWN) {
				speedWithGrav = MAX_SPEED_DOWN;
			}
			
			speed.setY(speedWithGrav);
			setPosition(position.additionne(speed));
		}
		
		
		
		if(isOutOfMap()) {
			
			respawn();
			
		}
	}
	public void beginDodge() {
		if(!isDodging && dodgeCooldown ==0 ) {
			isDodging = true;
			dodgingVec.setXYZ(speed.getX() *COEF_DODGING_SPEED ,0);
			if(speed.getY() < THRESHOLD_YSPEED_DODGE) {
				dodgingVec.setY(speed.getY()*COEF_DODGING_SPEED);
			}
			
			
			System.out.println(dodgingVec.getX());
		}
		
	}
	public void dodge() {
		if(!isOnGround) {
			speed.setXYZ(dodgingVec.getX(),dodgingVec.getY());
		}
		
		curDodgingFrame +=1;
		if(curDodgingFrame == DODGING_FRAME) {
			isDodging =false;
			dodgeCooldown = DODGE_COOLDOWN_MAX;
			curDodgingFrame = 0;
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
		if(canJump()) {
			speed.setY(JUMPING_POWER);
			curJumpCount++;
		}
		
	}
	public void moveX(boolean left) {
		if(left) {
			speed.setX(-SPEED_HORIZON);
		} else {
			speed.setX(SPEED_HORIZON);
		}
	}

	public boolean canJump() {
		return (curJumpCount < 2);
	}
	
	public void setPosition(Vecteur newPos) {
		position = newPos.copy();
		centrePos = new Vecteur(position.getX() + WIDTH / 2, position.getY() + HEIGHT/2);
		
	}
	public void respawn() {
		curJumpCount = 0;
		position = spawnPos.copy();
		speed.setXYZ(0, 0);
		isDodging = false;
		curDodgingFrame = 0 ;
		dodgeCooldown = 0;
	}
	

}
