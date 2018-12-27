package composantsmap;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Plateforme {
	private int x,y;
	private int width,height;
	
	private Rectangle2D.Double shapePlateforme;
	public Plateforme(int x,int y, int width,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		shapePlateforme = new Rectangle2D.Double(x,y,width,height);
	}
	public void dessiner(Graphics2D g2d) {
		g2d.fill(shapePlateforme);
	}
}
