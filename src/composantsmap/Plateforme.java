package composantsmap;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import geometrie.Vecteur;

public class Plateforme {
	public Vecteur  position = new Vecteur();
	private int width,height;
	
	private Rectangle2D.Double shapePlateforme;
	
	public Plateforme(int x,int y, int width,int height) {
		position.setX(x);
		position.setY(y);
		this.width = width;
		this.height = height;
		
		shapePlateforme = new Rectangle2D.Double(x,y,width,height);
	}
	
		
	public void dessinerDansEcran(Graphics2D g2d, Vecteur camera) {
	
		double xEcran = position.getX() - camera.getX();
		double yEcran = position.getY() - camera.getY();
		shapePlateforme.setRect(xEcran, yEcran, width, height);
		g2d.setColor(new Color(34,177,76));
		g2d.fill(shapePlateforme);
		
		
	}
	
	
}
