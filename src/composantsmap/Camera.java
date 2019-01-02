package composantsmap;

import geometrie.Vecteur;
import characters.Character;

public class Camera {
	public Vecteur position;
	public int width,height;
	
	public int vitesse = 3;
	/**
	 * 
	 * @param pos position vector  of the upper-left corner of the camera
	 * @param width the width of the vision of the camera in pixels
	 * @param height the height of the vision of the camera in pixels
	 */
	public Camera(Vecteur pos,int width,int height) {
		this.position = pos;
		this.width = width;
		this.height = height;
	}
	/**
	 * Updates the position of the camera according to the position of the characters in the map
	 * @param characters List of the players
	 */
	public void update(Character[] characters) {
		Vecteur centerPos;
		double xAverage=0,yAverage=0;
		for(Character c : characters) {
			xAverage+= c.position.getX();
			yAverage+= c.position.getY();
		}
		xAverage /= characters.length;
		yAverage /= characters.length;
		position = new Vecteur(xAverage - width / 2,yAverage - height/ 2);
	}
}
