package composantsmap;

import geometrie.Vecteur;
import characters.Character;

public class Camera {
	public final double COEF_MAX = 0.30;
	public final double COEF_CTC = 0.1; // Coef of the distance between a character and the camera zone
	public double maxDeltaPosX;
	public double maxDeltaPosY;
	public Vecteur position;
	public int width,height, mapWidth, mapHeight;
	
	public int vitesse = 3;
	/**
	 * 
	 * @param pos position vector  of the upper-left corner of the camera
	 * @param width the width of the vision of the camera in pixels
	 * @param height the height of the vision of the camera in pixels
	 */
	public Camera(Vecteur pos,int width,int height, int mapWidth, int mapHeight) {
		this.position = pos;
		this.width = width ;
		this.height = height;
		this.mapHeight = mapHeight;
		this.mapWidth = mapWidth;
		maxDeltaPosX = mapWidth*COEF_MAX;
		maxDeltaPosY = mapHeight*COEF_MAX;
	}
	/**
	 * Updates the position of the camera according to the position of the characters in the map
	 * @param characters List of the players
	 */
	public void update(Character[] characters) {
		Vecteur centerPos;
		double xAverage=0,yAverage=0;
		for(Character c : characters) {
			Vecteur posTransfo = inZoneMax(c.position);
			//double posX = c.position.getX();
			//double posY = c.position.getY();
			xAverage+= posTransfo.getX();
			yAverage+= posTransfo.getY();
			
		}
		//xAverage += mapWidth/2;
		//yAverage += mapHeight/2;
		xAverage /= characters.length ;
		yAverage /= characters.length ;
		position = new Vecteur(xAverage - width / 2,yAverage - height/ 2);
	}
	public Vecteur inZoneMax(Vecteur pos) {
		Vecteur vecTransfo = new Vecteur(pos.getX(),pos.getY());
		
		if(pos.getX() > mapWidth - maxDeltaPosX) {
			vecTransfo.setX(mapWidth - maxDeltaPosX);
		} else if(pos.getX() < maxDeltaPosX) {
			vecTransfo.setX(maxDeltaPosX);
		}
		
		if(pos.getY() > mapHeight - maxDeltaPosY) {
			vecTransfo.setY(mapHeight - maxDeltaPosY);
		} else if(pos.getY() < maxDeltaPosY) {
			vecTransfo.setY(maxDeltaPosY);
		}
		
		return vecTransfo;
	}
	public void camScaling() {
		
	}
}
