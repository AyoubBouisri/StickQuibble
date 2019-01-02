package composantsmap;

import geometrie.Vecteur;
import characters.Character;

public class Camera {
	public final double COEF_MAX = 0.35;
	public final double COEF_CTC = 0.1; // Coef of the distance between a character and the camera zone
	public final double COEF_CAMSPEED = 0.175;
	public double maxDeltaPosX;
	public double maxDeltaPosY;
	public Vecteur position;
	public Vecteur nextPosition; // the next position the camera 
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
		this.nextPosition = pos;
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
			xAverage+= posTransfo.getX();
			yAverage+= posTransfo.getY();
			
		}
		
		xAverage /= characters.length ;
		yAverage /= characters.length ;
		
		//gradual movement of the camera in case one of the character moves too fast
		position = position.additionne(nextPosition.soustrait(position).multiplie(COEF_CAMSPEED));
		nextPosition = new Vecteur(xAverage - width / 2,yAverage - height/ 2);
	}
	/**
	 * Method to return a position in the zone accepted for a character
	 * @param pos position of a character
	 * @return return the new position of the character that is within the zone accepted
	 */
	public Vecteur inZoneMax(Vecteur pos) {
		Vecteur vecTransfo = pos.copy();
		
		if(pos.getX() > mapWidth - maxDeltaPosX) {
			vecTransfo.setX(mapWidth - maxDeltaPosX);
		} else if(pos.getX() < maxDeltaPosX) {
			vecTransfo.setX(maxDeltaPosX);
		}
		
		if(pos.getY() > mapHeight - maxDeltaPosY) {
			vecTransfo.setY(mapHeight - maxDeltaPosY);
		} 
		
		
		return vecTransfo;
	}
	public void camScaling() {
		
	}
}
