package composantsmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import geometrie.Vecteur;
/**
 * 
 * Class that contains the information of a platform
 *
 */
public class Plateforme {
	public Vecteur  position = new Vecteur();
	public double width;
	public double height;
	
	public Plan floor;
	
	private Rectangle2D.Double shapePlateforme;
	/**
	 * Constructor of a platform
	 * @param x coordinate x of the top left position of the platform
	 * @param y coordinate y of the top left position of the platform
	 * @param width2 the width of the platform
	 * @param height2 the height of the platform
	 */
	public Plateforme(double x,double y, double width2,double height2) {
		position.setX(x);
		position.setY(y);
		this.width = width2;
		this.height = height2;
		
		 
		floor = new Plan(position,new Vecteur(x + width2,y));
		shapePlateforme = new Rectangle2D.Double(x,y,width2,height2);
	}
	
	/**
	 * Method that draws the platform according to the position of the camera	
	 * @param g2d Object that draws the platform
	 * @param camera vector position of the camera according to the map
	 */
	public void dessinerDansEcran(Graphics2D g2d, Camera camera) {
	
		double xEcran = position.getX() - camera.position.getX();
		double yEcran = position.getY() - camera.position.getY();
		shapePlateforme.setRect(xEcran, yEcran, width, height);
		g2d.setColor(new Color(34,177,76));
		g2d.fill(shapePlateforme);
		
		floor.dessinerDansEcran(g2d, camera);
		
		
	}
	
	
}
