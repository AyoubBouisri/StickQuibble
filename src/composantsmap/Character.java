package composantsmap;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur;

public class Character {
	public final static int JUMP_HEIGHT = 50;
	public final static int MAX_SPEED_DOWN = 30;
	public final static int ACCEL_Y = -30;
	public final static int JUMP_SPEED = 30;
	
	public int damage;
	public Vecteur speed;;
	public Ellipse2D.Double hitBox;
	public int width , height;
	public Vecteur position;
	public int curJumpCount = 0;

	
	public Character(Vecteur position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
		speed = new Vecteur();
		damage =0;
		hitBox = new Ellipse2D.Double(position.getX(), position.getY(), width, height);
	}
	
	public void dessinerDansEcran(Graphics2D g2d, Camera camera) {
		double xEcran = position.getX() - camera.position.getX();
		double yEcran = position.getY() - camera.position.getY();
		hitBox.setFrame(xEcran, yEcran, width, height);
		g2d.fill(hitBox);
	}
	public void jump() {
		speed.setY(30);
	}
	public boolean canJump() {
		return (curJumpCount==1 || curJumpCount==0);
	}
	
	
	

}
