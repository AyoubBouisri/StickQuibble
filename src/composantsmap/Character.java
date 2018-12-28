package composantsmap;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import geometrie.Vecteur;

public class Character {
	public int damage;
	public int speed;
	public Ellipse2D.Double hitBox;
	public int width , height;
	public Vecteur position;
	
	public Character(Vecteur position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
		hitBox = new Ellipse2D.Double(position.getX(), position.getY(), width, height);
	}
	
	public void dessinerDansEcran(Graphics2D g2d, Camera camera) {
		double xEcran = position.getX() - camera.position.getX();
		double yEcran = position.getY() - camera.position.getY();
		hitBox.setFrame(xEcran, yEcran, width, height);
		g2d.fill(hitBox);
	}
	
	

}
